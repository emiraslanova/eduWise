package com.example.eduwise.service;

import com.example.eduwise.mapper.UserMapper;
import com.example.eduwise.model.dto.RegistrationDto;
import com.example.eduwise.model.entity.User;
import com.example.eduwise.repository.UserRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final MailSender mailSender;
    private String baseUrl;

    public RegistrationService(UserRepository userRepository, UserMapper userMapper, MailSender mailSender) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailSender = mailSender;
    }


    public void register(RegistrationDto registrationDto) {
        User user = Optional.of(registrationDto).map(userMapper::toUser)
                .map(userRepository::save).orElseThrow();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registrationDto.getUsername());
        mailMessage.setSubject("registration confirmation");
        mailMessage.setText(baseUrl + "/registration/confirmation" + user.getUuid());
        mailSender.send(mailMessage);


    }

    public void confirmation(UUID uuid) {

    }
}
