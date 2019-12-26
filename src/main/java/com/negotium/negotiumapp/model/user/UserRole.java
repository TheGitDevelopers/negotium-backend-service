package com.negotium.negotiumapp.model.user;

import com.negotium.negotiumapp.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "description")
    @Size(max = 256)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}