package com.example.eduwise.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {

    private String notificationText;

    private String createdAt;

    private String isRead;


}
