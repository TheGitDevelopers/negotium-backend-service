package com.negotium.negotiumapp.domain;



import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "id_user", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID userUUID;

    @Column(name = "username", unique = true)
    @Size(max = 32, message = "{com.negotium.negotiumapp.domain.User.username.Size}")
    @NotNull(message = "{com.negotium.negotiumapp.domain.User.username.NotEmpty}")
    private String username;
//
    @Column(name = "password")
    @NotNull(message = "{com.negotium.negotiumapp.domain.User.password.NotEmpty}")
    @Size(min = 8, message = "{com.negotium.negotiumapp.domain.User.password.Size}")
    private String password;

    @Column(name = "email_address", unique = true)
    @Email(message = "{com.negotium.negotiumapp.domain.User.email.Email}")
    private String email;

   // @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   // private Set<UserRole> roles = new HashSet<>();



   // @OneToOne
   // private UserDetails details;

    public UUID getUserUUID() {
        return userUUID;
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

//    public List<UserRole> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<UserRole> roles) {
//        this.roles = roles;
//    }

   // public UserDetails getDetails() {
  //      return details;
  //  }

 //   public void setDetails(UserDetails details) {
    //    this.details = details;
  //  }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userUUID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
