package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.CertificateDto;
import com.example.eduwise.model.entity.Certificate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CertificateMapper  {
    Certificate toCertificate(CertificateDto certificateDto);

    CertificateDto toCertificateDto(Certificate savedCertificate);

    List<CertificateDto> toCertificateDtoList(List<Certificate> certificates);
}
