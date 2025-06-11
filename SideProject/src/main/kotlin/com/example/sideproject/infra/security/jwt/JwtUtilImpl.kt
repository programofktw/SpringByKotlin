package com.example.sideproject.infra.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.security.SignatureException
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtilImpl(

    @Value("\${jwt.secret}")
    val SECRET_KEY : String,

    @Value("\${jwt.access-token.expiration-time}")
    val ACCESS_TOKEN_EXPIRATION_TIME : Long,

    @Value("\${jwt.refresh-token.expiration-time}")
    val REFRESH_TOKEN_EXPIRATION_TIME : Long


) : JwtUtil {
    var log : Logger = LoggerFactory.getLogger(this::class.java)

    private fun getSigningKey(): SecretKey{
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    //TODO("이후 회원가입 여부 확인도 Token에 추가")
    override fun generateAccessToken(uuid: UUID) : String{
        log.info("액세스 토큰 발행.")
        return Jwts.builder()
            .claim("userId", uuid.toString())// 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
            .signWith(getSigningKey())
            .compact()
    }


    //TODO("이후 claim에서 userId 제거")
    //TODO("레디스를 도입한다면 JWT 방식의 R.T를 UUID 식으로 변경하면 어떨까?")
        //레디스를 활용하면 TTL로 유효기간이 지난 Token을 자동 삭제할 수 있음으로
        //JWT를 꼭 사용하지 않아도 되지 않을까 싶음.
    override fun generateRefreshToken(uuid: UUID) : String{
        log.info("리프레쉬 토큰 발행.")
        return Jwts.builder()
            .claim("userId", uuid.toString()) // 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis()))
            .signWith(getSigningKey())
            .compact()
    }

    /**
     * 헤더에서 AccessToken 추출하기
     */
    override fun getTokenFromHeader(httpServletRequest: HttpServletRequest): String {
        val authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)
            //TODO("토큰 없음 Exception 반환")
            ?: throw Exception()
        //hasText(token)토큰이 넘어왔는지 확인
        if(authorizationHeader.startsWith("Bearer ") && authorizationHeader.length > 7)
            return authorizationHeader.substring(7)
        else
            //TODO("Bearer 스킴이 없거나 형식이 잘못된 Authorization 헤더입니다")
            throw Exception()
    }

    //어차피 이 친구만 외부로 들어 내놓고
    //이후 토큰 검증, 클레임 받아오는 것, userId 빼오는것은 별도로 구성하면 되겠네...?
    //로직이 헤더 추출 메서드 호출 -> a.t를 포함하여 해당 메소드 호출 -> 검증 메서드 호출(Jwt 반환)-> Claim 반환 메서드 호출 (claim 반환) -> 해당 메서드에서 claim userId 추출 및 반환
    override fun getUserIdFromToken(token: String): String {
        val userId: String = getClaimsFromToken(validateToken(token)).get("userId", String::class.java)
        log.info("유저 id 반환")
        return userId
    }

    //claim을 받아올때랑 토큰을 검증할때 결국 같은 로직을 거침 그렇다면 이를 해결하여 중복을 제거해야하는 게 아닐까.
    //validation 체크를 할 때 JWT를 반환 받아오고, 이를 이후 여기의 매개변수로 보내는 방식
    private fun getClaimsFromToken(token: Jws<Claims>): Claims {
        return token.body
    }

    private fun validateToken(token: String) : Jws<Claims> {
        //parseClamisJWS에서 발생하는 예외를
        //본 프로젝트에서의 예외로 변경.
        return try{
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
        }catch (e : SignatureException){
            //TODO("토큰 유효하지 않다는 예외 제작")
            throw Exception()
        }catch (e : ExpiredJwtException){
            //TODO("토큰 만료되었다는 예외 제작")
            throw Exception()
        }catch (e: JwtException) {
            // 토큰이 유효하지 않은 경우
            log.warn("유효하지 않은 토큰입니다.")
            //TODO(토큰이 유효하지 않는 경우 반환하는 Exception을 만들어 처리)
            throw Exception()
        } catch (e: IllegalArgumentException) {
            log.warn("유효하지 않은 토큰입니다.")
            //TODO(토큰이 유효하지 않는 경우 반환하는 Exception을 만들어 처리)
            throw Exception()
        }catch (e : Exception){
            throw Exception()
        }
    }

    override fun signCheck(token: String): Boolean {
        TODO("Not yet implemented")
    }


}