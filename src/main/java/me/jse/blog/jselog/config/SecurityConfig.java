package me.jse.blog.jselog.config;

import me.jse.blog.jselog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // 빈등록 (IoC관리)
public class SecurityConfig {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    // authentication manager 빈 생성

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 어떤 방식으로 해쉬를 했는지 알아야 비교를 할 수 있음
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encode());
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeHttpRequests(auth ->
                auth.requestMatchers("/", "/auth/**", "/image/**", "/js/**", "/css/**", "/WEB-INF/**").permitAll()
                        .anyRequest().authenticated());
        http.formLogin(f -> f
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 이 경로로 로그인 요청을 보내면 따로 controller없어도 알아서 시큐리티가 로그인 처리를 해줌
                .defaultSuccessUrl("/", true)
                .permitAll()
        );

        return http.build();
    }

//    WEB-INF 폴더는 클라이언트가 직접 접근할 수 없는 보호된 경로임. 위처럼 WEB-INF 설정을 해놔도
}
