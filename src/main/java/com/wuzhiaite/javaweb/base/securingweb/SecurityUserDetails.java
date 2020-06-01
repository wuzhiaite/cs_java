package com.wuzhiaite.javaweb.base.securingweb;

import com.wuzhiaite.javaweb.common.authority.entity.UserRole;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * 用户信息类
 * @author lpf
 */
@ToString
public class SecurityUserDetails implements UserDetails, Serializable {
    private String username;
    private String password;
    private List<UserRole> userRoles;

    public SecurityUserDetails(){}

    public SecurityUserDetails(String username,String password,List<UserRole> userRoles){
        this.username = username ;
        this.password = password ;
        this.userRoles = userRoles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> gaList = new ArrayList();
        ListIterator<UserRole> iterator = userRoles.listIterator();
        for(;iterator.hasNext();){
            UserRole userRole = iterator.next();
            gaList.add(new SimpleGrantedAuthority(userRole.getRoleValue()));
        }
        return gaList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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



    public static void main(String[] args) {
        String password = "123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        System.out.println(encode);

        String p = "$2a$10$uX97Rn6R.I.gwEiAS4bseOlWVS77aAbmcRd5p0TXqhXi5TRYtpe1e";

        boolean matches = encoder.matches("123", p);
        System.out.println(matches);
    }



}
