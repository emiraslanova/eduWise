package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.ConfirmationTokenDto;
import com.example.eduwise.model.entity.ConfirmationToken;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.ConfirmationTokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/confirmationToken")
public class ConfirmationTokenController {

    private final ConfirmationTokenService confirmationTokenService;

    public ConfirmationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @PostMapping
    public BaseResponse<ConfirmationTokenDto> creatConfirmationToken(@Valid @RequestBody ConfirmationTokenDto
                                                                                 confirmationTokenDto){
        try {
            return BaseResponse.ok(confirmationTokenService.creatConfirmationToken(confirmationTokenDto));
        }catch (Exception e ){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<ConfirmationTokenDto> getAllConfirmationToken(){
        return confirmationTokenService.getAllConfirmationToken();
    }
    @GetMapping("/{id}")
    public BaseResponse<ConfirmationTokenDto> getByIdConfirmationToken(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(confirmationTokenService.getByIdConfirmationToken(id));
        }catch (Exception e ){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateConfirmationToken(@Valid @RequestBody ConfirmationTokenDto confirmationTokenDto ,
                                        @PathVariable Integer id) throws NotFoundException {
        confirmationTokenService.updateConfirmationToken(confirmationTokenDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteConfirmationToken(@Valid @PathVariable Integer id) throws NotFoundException {
        confirmationTokenService.deleteConfirmationToken(id);
    }
}
