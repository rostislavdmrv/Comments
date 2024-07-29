package com.tinqinacademy.comments.persistence.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name",nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 30)
    private String lastName;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "publish_date",nullable = false)
    private LocalDate publishDate;

    @Column(name = "last_edited_date",nullable = false)
    private LocalDate lastEditedDate;

    @Column(name = "last_edited_by",nullable = false)
    private String lastEditedBy;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;


}
