package com.example.eduwise.service;


import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.mapper.UserMapper;
import com.example.eduwise.model.dto.RegistrationDto;
import com.example.eduwise.model.entity.User;
import com.example.eduwise.repository.UserRepository;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final MailSender mailSender;
    private Queue<SimpleMailMessage>queue=new LinkedList<>();

    private String baseUrl ;

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
        mailMessage.setFrom("minavar@div.edu.az");
        mailMessage.setSubject("registration confirmation");
        mailMessage.setText(baseUrl + "/registration/confirmation" + user.getUuid());
        queue.add(mailMessage);
      //  mailSender.send(mailMessage);


    }

    public void confirmation(UUID uuid) {
        userRepository.findByUuid(uuid)
                .ifPresentOrElse(this::activateUser,()-> {throw new UserException("xeta");});

    }

    private void activateUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void  sendMail(){
        while (!queue.isEmpty()){
            mailSender.send(queue.poll());
        }

    }
}
