package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.MessageMapper;
import com.example.eduwise.model.dto.MessageDto;
import com.example.eduwise.model.entity.Message;
import com.example.eduwise.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public MessageDto creatMessage(MessageDto messageDto) throws NotFoundException {
        try {
            Message message = messageMapper.toMessage(messageDto);
            Message savedMessage = messageRepository.save(message);
            return messageMapper.toMessageDto(savedMessage);
        } catch (Exception e) {
            throw new NotFoundException("Message not created" + e.getMessage());
        }
    }

    public List<MessageDto> getAllMassage() {
        List<Message> messages = messageRepository.findAll();
        return messageMapper.toMessageDtoList(messages);
    }

    public MessageDto getByIdMessage(Integer id) throws NotFoundException {
        try {
            Message message = messageRepository.findById(id).orElse(null);
            return messageMapper.toMessageDto(message);
        } catch (Exception e) {
            throw new NotFoundException("Message not found id " + e.getMessage());
        }
    }

    public void updateMassage(MessageDto messageDto, Integer id) throws NotFoundException {
        try {
            Optional<Message> optionalMessage = messageRepository.findById(id);
            if (optionalMessage.isPresent()) {
                Message oldMessage = optionalMessage.get();
                Message newMessage = messageMapper.toMessage(messageDto);
                updateMessageFields(oldMessage, newMessage);
                messageRepository.save(oldMessage);
            }
        } catch (Exception e) {
            throw new NotFoundException("Message not update" + e.getMessage());
        }
    }

    private void updateMessageFields(Message oldMessage, Message newMessage) {
        oldMessage.setSenderId(newMessage.getSenderId());
        oldMessage.setMessageText(newMessage.getMessageText());
        oldMessage.setSentAt(newMessage.getSentAt());
        oldMessage.setIsRead(newMessage.getIsRead());
    }

    public void deleteMessage(Integer id) throws NotFoundException {
        try {
            messageRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Message not delete " + e.getMessage());
        }

    }
}
