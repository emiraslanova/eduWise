package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.ForgetPasswordTokenDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.ForgetPasswordTokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/forgetPasswordToken")
public class ForgetPasswordTokenController {

    private final ForgetPasswordTokenService forgetPasswordTokenService;

    public ForgetPasswordTokenController(ForgetPasswordTokenService forgetPasswordTokenService) {
        this.forgetPasswordTokenService = forgetPasswordTokenService;
    }

    @PostMapping
    public BaseResponse<ForgetPasswordTokenDto> creatForgetPasswordToken(
            @Valid @RequestBody ForgetPasswordTokenDto forgetPasswordTokenDto){
        try {
            return BaseResponse.ok(forgetPasswordTokenService.creatForget(forgetPasswordTokenDto));
        }catch (Exception e ){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<ForgetPasswordTokenDto> getAllForget(){
        return forgetPasswordTokenService.getAllForget();
    }
    @GetMapping("/{id}")
    public BaseResponse<ForgetPasswordTokenDto> getByIdForget(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(forgetPasswordTokenService.getByIdForget(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateForget(@Valid @RequestBody ForgetPasswordTokenDto forgetPasswordTokenDto,
                             @PathVariable Integer id) throws NotFoundException {
        forgetPasswordTokenService.updateForget(forgetPasswordTokenDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteForget(@Valid @PathVariable Integer id) throws NotFoundException {

        forgetPasswordTokenService.deleteForget(id);
    }
}
