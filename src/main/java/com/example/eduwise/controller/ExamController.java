package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.ExamDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @PostMapping
    public BaseResponse<ExamDto> creatExam(@Valid @RequestBody ExamDto examDto){
        try {
            return BaseResponse.ok(examService.creatExam(examDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<ExamDto> getAllExam(){
        return examService.getAllExam();
    }

    @GetMapping("/{id}")
    public BaseResponse<ExamDto> getByIdExam(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(examService.getByIdExam(id));
        }catch (Exception ex ){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateExam(@Valid @RequestBody ExamDto examDto ,@PathVariable Integer id) throws NotFoundException {
        examService.updateExam(examDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteExam(@Valid @PathVariable Integer id) throws NotFoundException {
        examService.deleteExam(id);
    }
}
