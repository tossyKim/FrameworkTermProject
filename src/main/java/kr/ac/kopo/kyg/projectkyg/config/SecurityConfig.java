package kr.ac.kopo.kyg.projectkyg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // URL 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // 정적 리소스 접근 허용
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        // 로그인 / 회원가입 페이지는 접근 허용
                        .requestMatchers("/members/login", "/members/new").permitAll()

                        // 그 외 모든 요청은 로그인 필요
                        .anyRequest().authenticated()
                )

                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/members/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/main", true)  // 로그인 성공 후 항상 main.html 이동
                        .failureUrl("/members/login?error")
                        .usernameParameter("memberId")
                        .passwordParameter("password")
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/members/logout")
                        .logoutSuccessUrl("/members/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
