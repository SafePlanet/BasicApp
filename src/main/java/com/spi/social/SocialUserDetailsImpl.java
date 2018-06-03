package com.spi.social;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.spi.service.dto.User;
 
public class SocialUserDetailsImpl implements SocialUserDetails {
 
    private static final long serialVersionUID = -5246117266247684905L;
 
    private List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    private User appUser;
 
    public SocialUserDetailsImpl(User appUser, List<String> roleNames) {
        this.appUser = appUser;
 
        for (String roleName : roleNames) {
 
            GrantedAuthority grant = new SimpleGrantedAuthority(roleName );
            this.list.add(grant);
        }
    }
 
    @Override
    public String getUserId() {
        return this.appUser.getId() + "";
    }
 
    @Override
    public String getUsername() {
        return appUser.getUsername();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return list;
    }
 
    @Override
    public String getPassword() {
        return appUser.getPassword();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
}