/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt;

import edu.uncg.dining.mgmt.models.Student;
import edu.uncg.dining.mgmt.models.User;
import edu.uncg.dining.mgmt.repositories.StudentRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author shrav
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
	private CustomAuthProvider authProvider;
    
    @Autowired
    private StudentRepository studentRepository;
    
    
    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home","/img/*.*","/css/*.*","/login","/student").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
                User user=(User) a.getPrincipal();
                String userType= user.getUsertype();
                if(userType.equalsIgnoreCase("manager")){
                    redirectStrategy.sendRedirect(hsr, hsr1, "/employees");
                } else if(userType.equalsIgnoreCase("supervisor")){
                    redirectStrategy.sendRedirect(hsr, hsr1, "/shiftss");
                } else{
                    Student student=studentRepository.findByUsername(user.getUsername());
                    redirectStrategy.sendRedirect(hsr, hsr1, "/customized/"+student.getStudentId());
                }  
            }
        })
                .and()
            .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }
    
    
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.authenticationProvider(authProvider);
	}
}