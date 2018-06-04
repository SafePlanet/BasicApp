package com.spi.social;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spi.dm.User;
import com.spi.repository.UserRepository;
  
@Service
@Transactional
public class UserDetailsServiceImpl  {
  
    @Autowired
    private UserRepository appUserDAO;
  
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
  
        System.out.println("UserDetailsServiceImpl.loadUserByUsername=" + userName);
  
        User appUser = this.appUserDAO.findByUsername(userName);
  
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
  
        System.out.println("Found User: " + appUser);
  
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = Arrays.asList("ROLE_USER");
  
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
  
        SocialUserDetailsImpl userDetails = new SocialUserDetailsImpl(appUser, roleNames);
  
        return userDetails;
    }
  
}
