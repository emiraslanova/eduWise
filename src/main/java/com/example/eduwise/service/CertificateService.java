package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.CertificateMapper;
import com.example.eduwise.model.dto.CertificateDto;
import com.example.eduwise.model.entity.Certificate;
import com.example.eduwise.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    public CertificateService(CertificateRepository certificateRepository, CertificateMapper certificateMapper) {
        this.certificateRepository = certificateRepository;
        this.certificateMapper = certificateMapper;
    }

    public CertificateDto creatCertificate(CertificateDto certificateDto) throws NotFoundException {
        try {
            Certificate certificate = certificateMapper.toCertificate(certificateDto);
            Certificate savedCertificate = certificateRepository.save(certificate);
            return certificateMapper.toCertificateDto(savedCertificate);
        } catch (Exception e) {
            throw new NotFoundException("Certificate not created" + e.getMessage());
        }

    }

    public List<CertificateDto> getAllCertificate() {
        List<Certificate> certificates = certificateRepository.findAll();
        return certificateMapper.toCertificateDtoList(certificates);

    }

    public CertificateDto getByIdCertificate(Integer id) throws NotFoundException {
        try {
            Certificate certificate = certificateRepository.findById(id).orElse(null);
            return certificateMapper.toCertificateDto(certificate);
        } catch (Exception e) {
            throw new NotFoundException("Certificate not found id" + e.getMessage());
        }

    }

    public void updateCertificate(CertificateDto certificateDto, Integer id) throws NotFoundException {
        try {
            Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
            if (optionalCertificate.isPresent()) {
                Certificate oldCertificate = optionalCertificate.get();
                Certificate newCertificate = certificateMapper.toCertificate(certificateDto);
                updateCertificateFields(oldCertificate, newCertificate);
                certificateRepository.save(oldCertificate);
            }
        } catch (Exception e) {
            throw new NotFoundException("Certificate not update , id " + e.getMessage());
        }
    }

    private void updateCertificateFields(Certificate oldCertificate, Certificate newCertificate) {
        oldCertificate.setIssueDate(newCertificate.getIssueDate());
    }

    public void deleteCertificate(Integer id) throws NotFoundException {
        try {
            certificateRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Certificate not delete , id" + e.getMessage());
        }

    }
}
