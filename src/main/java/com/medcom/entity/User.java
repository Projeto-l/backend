package com.medcom.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
