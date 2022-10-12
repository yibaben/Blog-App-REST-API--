package com.yibaben.fashionblogapi.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Like> likeList = new ArrayList<>();


}
