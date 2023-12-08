package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.ForgetPasswordTokenMapper;
import com.example.eduwise.model.dto.ForgetPasswordTokenDto;
import com.example.eduwise.model.entity.ForgetPasswordToken;
import com.example.eduwise.repository.ForgetPasswordTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForgetPasswordTokenService {

    private final ForgetPasswordTokenRepository passwordTokenRepository;
    private final ForgetPasswordTokenMapper passwordTokenMapper;

    public ForgetPasswordTokenService(ForgetPasswordTokenRepository passwordTokenRepository, ForgetPasswordTokenMapper passwordTokenMapper) {
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordTokenMapper = passwordTokenMapper;
    }

    public ForgetPasswordTokenDto creatForget(ForgetPasswordTokenDto forgetPasswordTokenDto) throws NotFoundException {
        try {
            ForgetPasswordToken forgetPasswordToken = passwordTokenMapper.toForgetPasswordToken(forgetPasswordTokenDto);
            ForgetPasswordToken savedToken = passwordTokenRepository.save(forgetPasswordToken);
            return passwordTokenMapper.toForgetPasswordTokenDto(savedToken);
        } catch (Exception e) {
            throw new NotFoundException("ForgetPasswordToken not created" + e.getMessage());
        }
    }

    public List<ForgetPasswordTokenDto> getAllForget() {
        List<ForgetPasswordToken> forgetPasswordTokens = passwordTokenRepository.findAll();
        return passwordTokenMapper.toForgetPasswordTokenDtoList(forgetPasswordTokens);
    }

    public ForgetPasswordTokenDto getByIdForget(Integer id) throws NotFoundException {
        try {
            ForgetPasswordToken forgetPasswordToken = passwordTokenRepository.findById(id).orElse(null);
            return passwordTokenMapper.toForgetPasswordTokenDto(forgetPasswordToken);
        } catch (Exception e) {
            throw new NotFoundException("ForgetPasswordToken not found id " + e.getMessage());
        }
    }

    public void updateForget(ForgetPasswordTokenDto forgetPasswordTokenDto, Integer id) throws NotFoundException {
        try {
            Optional<ForgetPasswordToken> optional = passwordTokenRepository.findById(id);
            if (optional.isPresent()) {
                ForgetPasswordToken oldForget = optional.get();
                ForgetPasswordToken newForget = passwordTokenMapper.toForgetPasswordToken(forgetPasswordTokenDto);
                updateForgetFields(oldForget, newForget);
                passwordTokenRepository.save(oldForget);
            }
        } catch (Exception e) {
            throw new NotFoundException("ForgetPasswordToken not update" + e.getMessage());
        }
    }

    private void updateForgetFields(ForgetPasswordToken oldForget, ForgetPasswordToken newForget) {
        oldForget.setToken(newForget.getToken());
        oldForget.setConfirmedAt(newForget.getConfirmedAt());
        oldForget.setConfirm(newForget.getConfirm());
    }

    public void deleteForget(Integer id) throws NotFoundException {
        try {
            passwordTokenRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("ForgetPasswordToken not delete " + e.getMessage());
        }

    }
}
