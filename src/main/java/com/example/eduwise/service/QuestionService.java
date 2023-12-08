package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.QuestionMapper;
import com.example.eduwise.model.dto.QuestionDto;
import com.example.eduwise.model.entity.Question;
import com.example.eduwise.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionDto creatQuestion(QuestionDto questionDto) throws NotFoundException {
        try {
            Question question = questionMapper.toQuestion(questionDto);
            Question savedQuestion = questionRepository.save(question);
            return questionMapper.toQuestionDto(savedQuestion);
        } catch (Exception e) {
            throw new NotFoundException("Question not created");
        }
    }

    public List<QuestionDto> getAllQuestion() {
        List<Question> questions = questionRepository.findAll();
        return questionMapper.toQuestionDtoList(questions);
    }

    public QuestionDto getByIdQuestion(Integer id) throws NotFoundException {
        try {
            Question question = questionRepository.findById(id).orElse(null);
            return questionMapper.toQuestionDto(question);
        } catch (Exception e) {
            throw new NotFoundException("Question not found " + e.getMessage());
        }
    }

    public void updateQuestion(QuestionDto questionDto, Integer id) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question oldQuestion = optionalQuestion.get();
            Question newQuestion = questionMapper.toQuestion(questionDto);
            updateQuestionFields(oldQuestion, newQuestion);
            questionRepository.save(oldQuestion);
            throw new NotFoundException("Question not update");
        }
    }

    private void updateQuestionFields(Question oldQuestion, Question newQuestion) {
        oldQuestion.setQuestionText(newQuestion.getQuestionText());
        oldQuestion.setOptionA(newQuestion.getOptionA());
        oldQuestion.setOptionB(newQuestion.getOptionB());
        oldQuestion.setOptionC(newQuestion.getOptionC());
        oldQuestion.setOptionD(newQuestion.getOptionD());
        oldQuestion.setCorrectOption(newQuestion.getCorrectOption());
    }

    public void deleteQuestion(Integer id) throws NotFoundException {

        questionRepository.deleteById(id);
        throw new NotFoundException("Question not delete");

    }
}
