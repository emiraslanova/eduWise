package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.NotificationDto;
import com.example.eduwise.model.entity.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toNotification(NotificationDto notificationDto);

    NotificationDto toNotificationDto(Notification savedNotification);

    List<NotificationDto> toNotificationDtoList(List<Notification> notifications);
}
