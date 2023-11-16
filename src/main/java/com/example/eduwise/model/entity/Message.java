package com.example.eduwise.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "driver",name = "message")
@Entity

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;

    @Column(name = "sender_id")
    private  int senderId;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "sent_at")
    private  String sentAt;

    @Column(name = "is_read")
    private String isRead;

    @ManyToOne
    private Course course;
    //course


}
