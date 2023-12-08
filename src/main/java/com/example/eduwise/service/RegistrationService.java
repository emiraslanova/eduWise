package com.example.eduwise.service;


import com.example.eduwise.component.AuthenticationFilter;
import com.example.eduwise.component.JwtTokenProvider;
import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.mapper.UserMapper;
import com.example.eduwise.model.dto.AuthenticationResponseDto;
import com.example.eduwise.model.dto.ChangePasswordRequest;
import com.example.eduwise.model.dto.ForgetPasswordRequest;
import com.example.eduwise.model.dto.RegistrationDto;
import com.example.eduwise.model.entity.User;
import com.example.eduwise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFilter authenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    private Queue<SimpleMailMessage> queue = new ConcurrentLinkedQueue<>();

    @Value("${app.registration.base-path}")
    private String baseUrl;

    private boolean isSending = true;


    public RegistrationService(UserRepository userRepository, UserMapper userMapper, JavaMailSender mailSender, PasswordEncoder passwordEncoder, AuthenticationFilter authenticationFilter, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailSender = mailSender;

        this.passwordEncoder = passwordEncoder;
        this.authenticationFilter = authenticationFilter;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void register(RegistrationDto registrationDto) {
        User user = Optional.of(registrationDto)
                .map(userMapper::toUser)
                .map(userRepository::save)
                .orElseThrow();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(registrationDto.getUsername());
        mailMessage.setFrom("amiraslanovamina1996@gmail.com");
        mailMessage.setSubject("registration confirmation");
        mailMessage.setText(baseUrl + "/registration/confirmation" + user.getUuid());
        queue.add(mailMessage);
        isSending = true;

    }
    // mailSender.send(mailMessage);


    public void confirmation(UUID uuid) {
        userRepository.findByUuid(uuid)
                .ifPresentOrElse(this::activateUser, () -> {
                    throw new UserException("xeta");
                });

    }

    private void activateUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMail() {
        log.info("starting...");
        while (!isSending && !queue.isEmpty()) {
            log.info("found email...");
            isSending = true;
            log.info("sending...");
            mailSender.send(queue.poll());
            log.info("sent 1");
        }
        isSending = false;
        log.info("sending finished");

    }

    public void resend(String email) {
        userRepository.findByUsername(email)
                .filter(user -> !user.isEnabled())
                .map(this::toMessage)
                .ifPresent(queue::add);
    }

    private SimpleMailMessage toMessage(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setFrom("amiraslanovamina1996@gmail.com");
        mailMessage.setSubject("registration confirmation");
        mailMessage.setText(baseUrl + "/registration/confirmation" + user.getUuid());
        return mailMessage;
    }


    public AuthenticationResponseDto login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String token = jwtTokenProvider.generateToken(username);
            authenticationFilter.setGeneratedToken(token);
            return AuthenticationResponseDto.builder().authToken(token).build();
        } else {
            throw new UserException("Invalid credentials.");
        }
    }
    public void forgetPassword(ForgetPasswordRequest request) {
        String username = request.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            String resetUuid = UUID.randomUUID().toString();
            user.setResetToken(UUID.fromString(resetUuid));
            userRepository.save(user);

            sendResetEmail(user);
        } else {
            throw new UserException("User not found");
        }
    }


    private void sendResetEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getUsername());
        mailMessage.setFrom("javid.a@div.edu.az");
        mailMessage.setSubject("Password Reset");
        mailMessage.setText(baseUrl + "/registration/reset-password/" + user.getResetToken());
        queue.add(mailMessage);
        isSending = true;
    }

    public void resetPassword(UUID resetUuid, String newPassword) {
        Optional<User> optionalUser = userRepository.findByResetToken(resetUuid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
        } else {
            throw new UserException("Invalid or expired Uuid");
        }
    }

}

