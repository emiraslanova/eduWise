package com.example.eduwise.service;

import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.mapper.AnswerMapper;
import com.example.eduwise.model.dto.AnswerDto;
import com.example.eduwise.model.entity.Answer;
import com.example.eduwise.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    public AnswerDto creatAnswer(AnswerDto answerDto) {
        try {
            Answer answer = answerMapper.toAnswer(answerDto);
            Answer savedAnswer = answerRepository.save(answer);
            return answerMapper.toAnswerDto(savedAnswer);
        }catch (Exception ex){
            throw new UserException("answer created error" + ex.getMessage());
        }
    }

    public List<AnswerDto> getAllAnswer() {
        List<Answer> answers = answerRepository.findAll();
        return answerMapper.toAnswerDtoList(answers);

    }

    public AnswerDto getByIdAnswer(Integer id) {
        try {
            Answer answer = answerRepository.findById(id).orElse(null);
            return answerMapper.toAnswerDto(answer);
        }catch (Exception ex){
            throw  new UserException("answer not found id " + ex.getMessage());
        }

    }

    public void updateAnswer(AnswerDto answerDto, Integer id) {
        try {
            Optional<Answer> answerOptional = answerRepository.findById(id);
            if(answerOptional.isPresent()){
                Answer oldAnswer = answerOptional.get();
                Answer newAnswer = answerMapper.toAnswer(answerDto);
                updateAnswerFields(oldAnswer,newAnswer);
                answerRepository.save(oldAnswer);
            }
        }catch (Exception ex){
            throw new UserException("Answer not update id" + ex.getMessage());
        }
    }
    private void updateAnswerFields(Answer oldAnswer, Answer newAnswer) {
        oldAnswer.setOptionA(newAnswer.getOptionA());
        oldAnswer.setOptionB(newAnswer.getOptionB());
        oldAnswer.setOptionC(newAnswer.getOptionC());
        oldAnswer.setOptionD(newAnswer.getOptionD());
        oldAnswer.setCorrectOption(newAnswer.getCorrectOption());
    }

    public void deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
        }catch (Exception ex ){
            throw new UserException("Answer not  delete " + ex.getMessage());
        }

    }
}
