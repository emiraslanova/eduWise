package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.QuestionDto;
import com.example.eduwise.model.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toQuestion(QuestionDto questionDto);

    QuestionDto toQuestionDto(Question savedQuestion);

    List<QuestionDto> toQuestionDtoList(List<Question> questions);
}
