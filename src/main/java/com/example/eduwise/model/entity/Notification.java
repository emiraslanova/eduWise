package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(schema = "driver",name = "notification")
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "notification_text")
    private String notificationText;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "is_read")
    private String isRead;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;


}
