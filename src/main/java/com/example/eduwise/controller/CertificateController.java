package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.CertificateDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.CertificateService;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public BaseResponse<CertificateDto> creatCertificate(@Valid @RequestBody CertificateDto certificateDto){
        try {
            return BaseResponse.ok(certificateService.creatCertificate(certificateDto));
        }catch (Exception e ){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<CertificateDto> getAllCertificate(){
        return certificateService.getAllCertificate();
    }
    @GetMapping("/{id}")
    public BaseResponse<CertificateDto> getByIdCertificate(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(certificateService.getByIdCertificate(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateCertificate(@Valid @RequestBody CertificateDto certificateDto ,@PathVariable Integer id) throws NotFoundException {
        certificateService. updateCertificate(certificateDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteCertificate(@Valid @PathVariable Integer id) throws NotFoundException {
        certificateService.deleteCertificate(id);

    }

}
