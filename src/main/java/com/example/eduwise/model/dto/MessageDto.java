package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {

    private  int senderId;

    private String messageText;

    private  String sentAt;

    private String isRead;

}
