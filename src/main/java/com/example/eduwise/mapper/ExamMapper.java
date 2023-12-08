package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.ExamDto;
import com.example.eduwise.model.entity.Exam;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    Exam toExam(ExamDto examDto);

    ExamDto toExamDto(Exam savedExam);

    List<ExamDto> toExamDtoList(List<Exam> exams);
}
