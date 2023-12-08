package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.ExamResultsDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.ExamResultsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/examResults")
public class ExamResultsController {

    private final ExamResultsService examResultsService;

    public ExamResultsController(ExamResultsService examResultsService) {
        this.examResultsService = examResultsService;
    }
    @PostMapping
    public BaseResponse<ExamResultsDto> creatExamResults(@Valid @RequestBody ExamResultsDto examResultsDto){
        try {
            return BaseResponse.ok(examResultsService.creatExamResults(examResultsDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<ExamResultsDto> getAllExamResults(){
        return examResultsService.getAllExamResults();
    }
    @GetMapping("/{id}")
    public BaseResponse<ExamResultsDto> getByIdExamResults(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(examResultsService.getByIdExamResults(id));
        }catch (Exception e){
           return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateExamResults(@Valid @RequestBody ExamResultsDto examResultsDto,@PathVariable Integer id) throws NotFoundException {
        examResultsService.updateExamResults(examResultsDto,id);
    }
    @DeleteMapping("{id}")
    public void deleteExamResults(@Valid @PathVariable Integer id) throws NotFoundException {
        examResultsService.deleteExamResults(id);
    }

}
