package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.NotificationMapper;
import com.example.eduwise.model.dto.NotificationDto;
import com.example.eduwise.model.entity.Notification;
import com.example.eduwise.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public NotificationDto creatNotification(NotificationDto notificationDto) throws NotFoundException {
        try {
            Notification notification = notificationMapper.toNotification(notificationDto);
            Notification savedNotification = notificationRepository.save(notification);
            return notificationMapper.toNotificationDto(savedNotification);
        } catch (Exception e) {
            throw new NotFoundException("Notification not created" + e.getMessage());
        }

    }

    public List<NotificationDto> getAllNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.toNotificationDtoList(notifications);
    }

    public NotificationDto getByIdNotification(Integer id) throws NotFoundException {
        try {
            Notification notification = notificationRepository.findById(id).orElse(null);
            return notificationMapper.toNotificationDto(notification);
        } catch (Exception e) {
            throw new NotFoundException("Notification not found id" + e.getMessage());
        }

    }

    public void updateNotification(NotificationDto notificationDto, Integer id) throws NotFoundException {
        Optional<Notification> optional = notificationRepository.findById(id);
        if (optional.isPresent()) {
            Notification oldNotification = optional.get();
            Notification newNotification = notificationMapper.toNotification(notificationDto);
            updateNotificationFields(oldNotification, newNotification);
            notificationRepository.save(oldNotification);
            throw new NotFoundException("Notification not update");
        }
    }

    private void updateNotificationFields(Notification oldNotification, Notification newNotification) {
        oldNotification.setNotificationText(newNotification.getNotificationText());
        oldNotification.setCreatedAt(newNotification.getCreatedAt());
        oldNotification.setIsRead(newNotification.getIsRead());

    }

    public void deleteNotification(Integer id) throws NotFoundException {
        notificationRepository.deleteById(id);
        throw new NotFoundException("Notification not delete");

    }
}
