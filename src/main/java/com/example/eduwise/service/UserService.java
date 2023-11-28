package com.example.eduwise.service;

import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.mapper.UserMapper;
import com.example.eduwise.model.dto.UserDto;
import com.example.eduwise.model.entity.User;
import com.example.eduwise.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private  final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }


    public UserDto creatUser(UserDto userDto) {
        try {
            User user = userMapper.toUser(userDto);
            User savedUser = userRepository.save(user);
            return userMapper.toUserDto(savedUser);
        } catch (Exception ex) {
            throw new UserException("User creation error" + ex.getMessage());
        }
    }

    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return userMapper.toUserDtoList(users);
    }

    public UserDto getByIdUser(Integer id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            return userMapper.toUserDto(user);
        } catch (Exception ex) {
            throw new UserException("User not found,id" + ex.getMessage());
        }
    }

    public void updateByIdUser(UserDto userDto, Integer id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User oldUser = userOptional.get();
                User newUser = userMapper.toUser(userDto);
                updateUserFields(oldUser, newUser);
                userRepository.save(oldUser);
            }
        } catch (Exception ex) {
            throw new UserException("User not updated,id" + ex.getMessage());
        }
    }
        private void updateUserFields (User oldUser, User newUser){
            oldUser.setName(newUser.getName());
            oldUser.setSurname(newUser.getSurname());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPhoneNumber(newUser.getPhoneNumber());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setBirthdate(newUser.getBirthdate());
            oldUser.setRegistrationDate(newUser.getRegistrationDate());
        }


    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);

        }catch (Exception ex){
            throw new UserException("User not delete"+ex.getMessage());
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                username, encoder.encode("guVenli55"), Collections.emptyList());
        return user;
    }
}
