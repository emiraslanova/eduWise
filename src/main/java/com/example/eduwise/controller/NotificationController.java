package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.NotificationDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public BaseResponse<NotificationDto> creatNotification(@Valid @RequestBody NotificationDto notificationDto){
        try {
            return BaseResponse.ok(notificationService.creatNotification(notificationDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<NotificationDto> getAllNotification(){
        return notificationService.getAllNotification();
    }
    @GetMapping("/{id}")
    public BaseResponse<NotificationDto> getByIdNotification(@Valid @PathVariable Integer id ){
        try {
            return BaseResponse.success(notificationService.getByIdNotification(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public  void  updateNotification(@Valid @RequestBody NotificationDto notificationDto,@PathVariable Integer id ) throws NotFoundException {
        notificationService.updateNotification(notificationDto,id);
    }
    @DeleteMapping("/{id}")

    public void deleteNotification(@Valid @PathVariable Integer id ) throws NotFoundException {
        notificationService.deleteNotification(id);
    }

}
