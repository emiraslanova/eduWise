package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.RegistrationDto;
import com.example.eduwise.model.dto.UserDto;
import com.example.eduwise.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = UUID.class)
public abstract  class UserMapper  {

    @Autowired
    protected PasswordEncoder passwordEncoder;

   public abstract UserDto toUserDto(User user);

     public abstract   User toUser(UserDto userDto);

    public abstract   List<UserDto> toUserDtoList(List<User>users);


     @Mapping(target = "enabled", constant = "true")
     @Mapping(target = "credentialsNonExpired", constant = "true")
     @Mapping(target = "accountNonLocked", constant = "true")
     @Mapping(target = "accountNonExpired", constant = "true")
     @Mapping(target = "password", expression = "java(passwordEncoder.encode(registrationDto.getPassword()))")
     @Mapping(target = "uuid",expression = "java(UUID.randomUUID())")
      public abstract User  toUser(RegistrationDto registrationDto);
}
