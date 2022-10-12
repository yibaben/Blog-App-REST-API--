package com.yibaben.fashionblogapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String postImage;
    private String slug;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    private List<Like> likeLike = new ArrayList<>();


}
