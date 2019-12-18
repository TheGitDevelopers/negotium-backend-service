package com.negotium.negotiumapp.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username", unique = true)
    @Size(max = 32, message = "{com.negotium.negotiumapp.model.User.username.Size}")
    @NotNull(message = "{com.negotium.negotiumapp.model.User.username.NotEmpty}")
    private String username;

    @Column(name = "password")
    @NotNull(message = "{com.negotium.negotiumapp.model.User.password.NotEmpty}")
    @Size(min = 8, message = "{com.negotium.negotiumapp.model.User.password.Size}")
    private String password;

    @Column(name = "email_address", unique = true)
    @Email(message = "{com.negotium.negotiumapp.model.User.email.Email}")
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)  //TODO: We must fix it
    private Set<UserRole> roles = new HashSet<>();


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
