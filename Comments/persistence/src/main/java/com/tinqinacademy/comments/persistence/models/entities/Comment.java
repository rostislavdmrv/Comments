package com.tinqinacademy.comments.persistence.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
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

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @CreationTimestamp
    @Column(name = "publish_date", updatable = false)
    private LocalDateTime publishDate;

    @UpdateTimestamp
    @Column(name = "last_edited_date")
    private LocalDateTime lastEditedDate;

    @Column(name = "last_edited_by")
    private UUID lastEditedBy;
}
