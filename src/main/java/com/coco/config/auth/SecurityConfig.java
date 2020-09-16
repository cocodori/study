package com.coco.config.auth;

import com.coco.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() //h2-console.화면을 사용하기 위해 해당 옵션을 비활성화한다.
                .and()
                    .authorizeRequests()    //URL별 권한 관리 설정하는 옵션의 시작
                    .antMatchers("/", "/css/**", "/image/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() //설정된 값 외 나머지 URL
                .and()
                    .logout()  //로그아웃 핸들러
                    .logoutSuccessUrl("/")
                .and()  //로그인 핸들러
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
