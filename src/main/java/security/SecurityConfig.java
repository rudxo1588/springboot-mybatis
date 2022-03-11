package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mybatis.demo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
/* @EnableWebSecurity
	spring security를 활성화 시켜준다.
	
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	Controller에서 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우 
	@PreAuthorize 어노테이션을 사용한다.
	해당 어노테이션에 대한 설정을 활성화 시키는 어노테이션이다. 필수는 아님.
*/ 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/*
	 * WebSecurity는 FilterChainProxy를 생성하는 필터이다.
	 * 다양한 필터 설정을 적용할수 있다.
	 * 해당 설정을 통해 Spring Security에서 해당 요청은 인증대상에서 제외시킨다.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
	/*
	 * HttpSecurity를 통해 HTTP에 대한 보안을 설정할 수 있다.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
        .antMatchers("/member/**").authenticated() // member인증을 받은 사용자만 가능
        .antMatchers("/admin/**").authenticated() // admin인증을 받은 사용자만 가능
        .antMatchers("/**").permitAll(); // 모든 경로로 요청이 가능

		http.formLogin()
		        .loginPage("/login")
		        .defaultSuccessUrl("/")
		        .permitAll();
		
		http.logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login")
		        .invalidateHttpSession(true);
		
		http.exceptionHandling()
		        .accessDeniedPage("/denied");
	}
	
	 @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}

