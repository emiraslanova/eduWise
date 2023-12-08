package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.MessageDto;
import com.example.eduwise.model.entity.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    Message toMessage(MessageDto messageDto);

    MessageDto toMessageDto(Message savedMessage);


    List<MessageDto> toMessageDtoList(List<Message> messages);
}
