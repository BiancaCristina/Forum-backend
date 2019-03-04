package com.github.biancacristina.Forum.security;

import com.github.biancacristina.Forum.domain.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails{
    // Class that defines the User Spring Security (UserSS)

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String pw;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS() {}

    public UserSS(Integer id, String email, String pw, Set<Profile> perfis) {
        super();
        this.id = id;
        this.email = email;
        this.pw = pw;

        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returns the auths of the user
        return authorities;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        // The username used to login is the email, so returns it
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // By default, account will never be expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // By default, account will never be locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // By default, credential will never expired
        return true;
    }

    @Override
    public boolean isEnabled() {
        // By default, user will always be enabled
        return true;
    }
}
