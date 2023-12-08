package com.example.eduwise.controller;

import com.example.eduwise.model.dto.AnswerDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.AnswerService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
public class AnswerController {

    private final AnswerService answerService;


    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @PostMapping
    @PermitAll
    public BaseResponse<AnswerDto> creatAnswer(@Valid @RequestBody AnswerDto answerDto){
        try {
            return BaseResponse.ok(answerService.creatAnswer(answerDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }

    }
    @GetMapping
    public List<AnswerDto> getAllAnswer(){
        return answerService.getAllAnswer();
    }

    @GetMapping("/{id}")
    public BaseResponse<AnswerDto>getByIdAnswer(@PathVariable Integer id){
        try {
            return BaseResponse.success(answerService.getByIdAnswer(id));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void  updateAnswer(@RequestBody AnswerDto answerDto,@PathVariable Integer id ){
        answerService.updateAnswer(answerDto,id);
    }

    @DeleteMapping("/{id}")

    public void  deleteAnswer(@PathVariable Integer id){
        answerService.deleteAnswer(id);
    }


}
