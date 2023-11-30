package com.example.eduwise.service;

import com.example.eduwise.mapper.AnswerMapper;
import com.example.eduwise.model.dto.AnswerDto;
import com.example.eduwise.repository.AnswerRepository;
import com.example.eduwise.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    public AnswerDto creatAnswer(AnswerDto answerDto) {

        return null;
    }

    public BaseResponse<AnswerDto> getAllAnswer() {
        return null;
    }

    public AnswerDto getByIdAnswer(Integer id) {
        return null;
    }

    public void updateAnswer(AnswerDto answerDto, Integer id) {

    }

    public void deleteAnswer(Integer id) {

    }
}
