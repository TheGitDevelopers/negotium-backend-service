package com.negotium.negotiumapp.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_details")
    private Long id;

    @Column(name = "telephone_number")
    private int telephone_Number;

    @Column(name = "avatar_url")
    private String avatar;

    public UserDetails() {
    }

    public UserDetails(int telephone_Number, String avatar) {
        this.telephone_Number = telephone_Number;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTelephone_Number() {
        return telephone_Number;
    }

    public void setTelephone_Number(int telephone_Number) {
        this.telephone_Number = telephone_Number;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserDatails{" +
                "id=" + id +
                ", telephone_Number=" + telephone_Number +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
