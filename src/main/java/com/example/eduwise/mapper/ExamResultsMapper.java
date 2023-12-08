package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.ExamResultsDto;
import com.example.eduwise.model.entity.ExamResults;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultsMapper {
    ExamResults toExamResults(ExamResultsDto examResultsDto);

    ExamResultsDto toExamResultsDto(ExamResults savedExamResults);

    List<ExamResultsDto> toExamResultsDtoList(List<ExamResults> examResults);
}
