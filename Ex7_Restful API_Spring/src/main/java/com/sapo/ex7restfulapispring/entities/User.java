package com.sapo.ex7restfulapispring.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_users")
@Data
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = -1956195527415323516L;

    @Column(name = "username", length = 45, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_users_roles",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    // phân quyền
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
