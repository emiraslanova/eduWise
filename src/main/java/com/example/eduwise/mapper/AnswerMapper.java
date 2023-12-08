package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.AnswerDto;
import com.example.eduwise.model.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public  interface AnswerMapper {

    Answer toAnswer(AnswerDto answerDto);

    AnswerDto toAnswerDto(Answer answer);

    List<AnswerDto> toAnswerDtoList(List<Answer> answers);
}
