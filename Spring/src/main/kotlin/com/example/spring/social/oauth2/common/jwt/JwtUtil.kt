import com.example.spring.social.oauth2.common.jwt.error.TokenErrorResult
import com.example.spring.social.oauth2.common.jwt.error.TokenException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import java.util.Date
import java.util.UUID

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private lateinit var SECRET_KEY: String

    private val log = LoggerFactory.getLogger(this.javaClass)!!


    private fun getSigningKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    // 액세스 토큰을 발급하는 메서드
    fun generateAccessToken(userId: UUID, expirationMillis: Long): String {
        return Jwts.builder()
            .claim("userId", userId.toString()) // 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(getSigningKey())
            .compact()
    }

    // 리프레쉬 토큰을 발급하는 메서드
    fun generateRefreshToken(userId: UUID, expirationMillis: Long): String {
        log.info("리프레쉬 토큰이 발행되었습니다.")

        return Jwts.builder()
            .claim("userId", userId.toString()) // 클레임에 userId 추가
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(getSigningKey())
            .compact()
    }

    // 응답 헤더에서 액세스 토큰을 반환하는 메서드
    fun getTokenFromHeader(authorizationHeader: String): String {
        return authorizationHeader.substring(7)
    }

    // 토큰에서 유저 id를 반환하는 메서드
    fun getUserIdFromToken(token: String): String {
        return try {
            val userId = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body["userId"] as String
            log.info("유저 id를 반환합니다.")
            userId
        } catch (e: JwtException) {
            // 토큰이 유효하지 않은 경우
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        } catch (e: IllegalArgumentException) {
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        }
    }

    // Jwt 토큰의 유효기간을 확인하는 메서드
    fun isTokenExpired(token: String): Boolean {
        return try {
            val expirationDate = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body.expiration
            log.info("토큰의 유효기간을 확인합니다.")
            expirationDate.before(Date())
        } catch (e: JwtException) {
            // 토큰이 유효하지 않은 경우
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        } catch (e: IllegalArgumentException) {
            log.warn("유효하지 않은 토큰입니다.")
            throw TokenException(TokenErrorResult.INVALID_TOKEN)
        }
    }
}
