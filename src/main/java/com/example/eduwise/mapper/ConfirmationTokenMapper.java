package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.ConfirmationTokenDto;
import com.example.eduwise.model.entity.ConfirmationToken;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfirmationTokenMapper  {

    ConfirmationToken toConfirmationToken(ConfirmationTokenDto confirmationTokenDto);

    ConfirmationTokenDto toConfirmationTokenDto(ConfirmationToken savedConfirmationToken);

    List<ConfirmationTokenDto> toConfirmationTokenDtoList(List<ConfirmationToken> confirmationTokens);
}
