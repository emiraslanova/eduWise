package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.MessageDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/massages")
public class MassageController {

    private final MessageService messageService;

    public MassageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping
    public BaseResponse<MessageDto> creatMessage(@Valid @RequestBody MessageDto messageDto){
        try {
            return BaseResponse.ok(messageService.creatMessage(messageDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<MessageDto> getAllMessage(){
        return messageService.getAllMassage();

    }
    @GetMapping("/{id}")
    public BaseResponse<MessageDto> getByIdMessage(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(messageService.getByIdMessage(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void UpdateMessage(@Valid @RequestBody MessageDto messageDto,@PathVariable Integer id) throws NotFoundException {
        messageService.updateMassage(messageDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteMessage(@Valid @PathVariable Integer id) throws NotFoundException {
        messageService.deleteMessage(id);
    }
}
