package com.example.spring.security

import com.example.spring.security.oauth2.OAuthLoginFailureHandler
import com.example.spring.security.oauth2.OAuthLoginSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import java.util.*


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val  oAuthLoginSuccessHandler : OAuthLoginSuccessHandler,
    private val oAuthLoginFailureHandler : OAuthLoginFailureHandler

) {

    // CORS 설정
    fun corsConfigurationSource(): CorsConfigurationSource {
        return CorsConfigurationSource { request ->
            val config = CorsConfiguration()
            config.allowedHeaders = Collections.singletonList("*")
            config.setAllowedMethods(Collections.singletonList("*"))
            config.setAllowedOriginPatterns(Collections.singletonList("*")) // 허용할 origin
            config.allowCredentials = true
            config
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
            //cors 설정
            .cors { corsConfigurer: CorsConfigurer<HttpSecurity?> ->
                corsConfigurer.configurationSource(
                    corsConfigurationSource()
                )
            } // CORS 설정 추가

            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .authorizeHttpRequests(
                Customizer { authorize ->
                    authorize
                        .requestMatchers("/index.html").permitAll()
                        .anyRequest().authenticated()       //로그인 이후엔 모두 허용
                }
            )
            .oauth2Login { oauth: OAuth2LoginConfigurer<HttpSecurity?> ->  // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                oauth
                    .successHandler(oAuthLoginSuccessHandler) // 로그인 성공 시 핸들러
                    .failureHandler(oAuthLoginFailureHandler) // 로그인 실패 시 핸들러
            }
            .sessionManagement {
                Customizer { session: SessionManagementConfigurer<HttpSecurity?> ->
                    session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
            }

        return httpSecurity.build()
    }
}