package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.login.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   private final UserService userService;
   
   @Bean
   public SecurityFilterChain filerChain(HttpSecurity http) throws Exception {
        
      http.csrf().disable();
      
      //security가 제공해주는 로그인 폼 말고 우리가 만들거야
      http.authorizeHttpRequests() //내가 원하는 인증과 인가에 대한 설정을 한다
      // 정적파일 permit
      .requestMatchers("/css/**","/error","/styles/**",  "/assets/**","/images/**","/profileImage/**","/summernote/**").permitAll()
      .requestMatchers("", "/", "/mainPage", "main_updateTalk", "/searchresult" // 여기까지 mainPage 관련 mapping 
            , "/layout/default_layout", // 여기까지 layout 관련 mapping 
             "/sign", "/login/findid", "/login/findidcomplete", "/checkInfo", "/login/findpassword"
            , "/login/passwordreset", "/changePwd", "/login/passwordresetcomplete" // 여기까지 login 관련 mapping 
            , "/accomodation/**", "/activity/**","/community/**","/footer/**","/header/**","/mypage/**"
            , "/js/**", "/images/**", "/styles/**" //TRIPJAVA 추가
    		  ).permitAll() //이 요청은 모두 사용가능
      .anyRequest().authenticated(); //나머지는 인증 필요
      
      http.formLogin().loginPage("/header/original_login").permitAll() //로그인은 여기서해
      .defaultSuccessUrl("/activity/activityMain"); //성공하면 main으로 갈게
   
      
      http.logout() //패턴 링크만 적어주면 알아서 로그아웃을 해줄거야
      .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout")) //패턴이름 logout
      .invalidateHttpSession(true)   //로그아웃하면 session을 파기해
      .logoutSuccessUrl("/header/original_login") //로그아웃 성공하면 이리로가
       .and()
       .oauth2Login()
       .loginPage("/header/original_login")
       .defaultSuccessUrl("/activity/activityMain")
       .failureUrl("/header/original_login")
       .userInfoEndpoint()
       .userService(userService);
      
      http.httpBasic(); //나머지는 기본 설정에 따라라
      
      return http.build();
   }
   
}