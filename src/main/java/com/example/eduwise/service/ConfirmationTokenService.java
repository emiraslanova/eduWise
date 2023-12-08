package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.ConfirmationTokenMapper;
import com.example.eduwise.model.dto.ConfirmationTokenDto;
import com.example.eduwise.model.entity.Certificate;
import com.example.eduwise.model.entity.ConfirmationToken;
import com.example.eduwise.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository conRepo;
    private final ConfirmationTokenMapper conMap;

    public ConfirmationTokenService(ConfirmationTokenRepository conRepo, ConfirmationTokenMapper conMap) {
        this.conRepo = conRepo;
        this.conMap = conMap;
    }

    public ConfirmationTokenDto creatConfirmationToken(ConfirmationTokenDto confirmationTokenDto) throws NotFoundException {
        try {
            ConfirmationToken confirmationToken = conMap.toConfirmationToken(confirmationTokenDto);
            ConfirmationToken savedConfirmationToken = conRepo.save(confirmationToken);
            return conMap.toConfirmationTokenDto(savedConfirmationToken);
        }catch (Exception ex ){
            throw new NotFoundException("ConfirmationToken not created , id" + ex.getMessage());
        }
    }

    public List<ConfirmationTokenDto> getAllConfirmationToken() {
        List <ConfirmationToken>confirmationTokens = conRepo.findAll();
        return conMap.toConfirmationTokenDtoList(confirmationTokens);
    }

    public ConfirmationTokenDto getByIdConfirmationToken(Integer id) throws NotFoundException {
        try {
            ConfirmationToken confirmationToken = conRepo.findById(id).orElse(null);
            return conMap.toConfirmationTokenDto(confirmationToken);
        }catch (Exception ex){
            throw  new NotFoundException("ConfirmationToken not found id " + ex.getMessage());
        }
    }

    public void updateConfirmationToken(ConfirmationTokenDto confirmationTokenDto, Integer id) throws NotFoundException {
        try {
            Optional<ConfirmationToken>optionalConfirmationToken = conRepo.findById(id);
            if (optionalConfirmationToken.isPresent()) {
               ConfirmationToken oldConfirmationToken = optionalConfirmationToken.get();
               ConfirmationToken newConfirmationToken = conMap.toConfirmationToken(confirmationTokenDto);
                updateConfirmationTokenFields(oldConfirmationToken,newConfirmationToken);
               conRepo.save(oldConfirmationToken);
            }
        } catch (Exception e) {
            throw new NotFoundException("ConfirmationToken not update , id " + e.getMessage());
        }
    }
    private void updateConfirmationTokenFields(ConfirmationToken oldConfirmationToken, ConfirmationToken newConfirmationToken) {
        oldConfirmationToken.setToken(newConfirmationToken.getToken());
        oldConfirmationToken.setConfirmedAt(newConfirmationToken.getConfirmedAt());
        oldConfirmationToken.setConfirm(newConfirmationToken.getConfirm());
    }


    public void deleteConfirmationToken(Integer id) throws NotFoundException {
        try {
            conRepo.deleteById(id);
        }catch (Exception e){
            throw new NotFoundException("ConfirmationToken not delete id" + e.getMessage());
        }

    }
}
