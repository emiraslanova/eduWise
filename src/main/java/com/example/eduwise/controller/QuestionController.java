package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.QuestionDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public BaseResponse<QuestionDto> creatQuestion(@Valid @RequestBody QuestionDto questionDto) {
        try {
            return BaseResponse.ok(questionService.creatQuestion(questionDto));
        } catch (Exception e) {
            return BaseResponse.fail();
        }
    }

    @GetMapping
    public List<QuestionDto> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/{id}")
    public BaseResponse<QuestionDto> getByIdQuestion(@Valid @PathVariable Integer id) {
        try {
            return BaseResponse.success(questionService.getByIdQuestion(id));
        } catch (Exception e) {
            return BaseResponse.fail();
        }
    }

    @PutMapping("/{id}")
    public void updateQuestion(@Valid @RequestBody QuestionDto questionDto, @PathVariable Integer id) throws NotFoundException {
        questionService.updateQuestion(questionDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@Valid @PathVariable Integer id) throws NotFoundException {
       questionService.deleteQuestion(id);
    }
}
