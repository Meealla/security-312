package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Column(name = "role")
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Role(String role, Long id) {
        this.role = role;
        this.id = id;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
