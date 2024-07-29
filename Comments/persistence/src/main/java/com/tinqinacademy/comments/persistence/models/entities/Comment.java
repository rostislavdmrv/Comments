package com.tinqinacademy.comments.persistence.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "room_no", nullable = false, unique = true,length = 10)
    private String roomNo;


    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    private Article article;
}
