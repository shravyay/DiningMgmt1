/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncg.dining.mgmt;

import edu.uncg.dining.mgmt.models.User;
import edu.uncg.dining.mgmt.repositories.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author komalkubsad
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider{
    
    @Autowired
    UserRepository userRepository;
    

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String name=authentication.getName();
       String password = authentication.getCredentials().toString();
       User user = userRepository.findByUsernameAndPassword(name,password);
       if(user!=null){
           return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),new ArrayList<GrantedAuthority>());	
       }
       return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        	return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
