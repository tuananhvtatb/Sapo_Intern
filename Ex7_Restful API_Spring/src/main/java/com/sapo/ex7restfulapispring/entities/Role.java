package com.sapo.ex7restfulapispring.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tbl_roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = -1246332751520787944L;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "description", length = 45, nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
