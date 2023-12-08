package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.ForgetPasswordTokenDto;
import com.example.eduwise.model.entity.ForgetPasswordToken;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForgetPasswordTokenMapper  {
    ForgetPasswordToken toForgetPasswordToken(ForgetPasswordTokenDto forgetPasswordTokenDto);

    ForgetPasswordTokenDto toForgetPasswordTokenDto(ForgetPasswordToken savedToken);

    List<ForgetPasswordTokenDto> toForgetPasswordTokenDtoList(List<ForgetPasswordToken> forgetPasswordTokens);
}
