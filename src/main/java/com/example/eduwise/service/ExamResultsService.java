package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.ExamResultsMapper;
import com.example.eduwise.model.dto.ExamResultsDto;
import com.example.eduwise.model.entity.ExamResults;
import com.example.eduwise.repository.ExamRepository;
import com.example.eduwise.repository.ExamResultsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultsService {
    private final ExamResultsRepository resultsRepository;
    private final ExamResultsMapper resultsMapper;

    public ExamResultsService(ExamResultsRepository resultsRepository, ExamResultsMapper resultsMapper) {
        this.resultsRepository = resultsRepository;
        this.resultsMapper = resultsMapper;
    }

    public ExamResultsDto creatExamResults(ExamResultsDto examResultsDto) throws NotFoundException {
        try {
            ExamResults examResults = resultsMapper.toExamResults(examResultsDto);
            ExamResults savedExamResults = resultsRepository.save(examResults);
            return resultsMapper.toExamResultsDto(savedExamResults);
        } catch (Exception e) {
            throw new NotFoundException("ExamResults not created" + e.getMessage());
        }
    }

    public List<ExamResultsDto> getAllExamResults() {
        List<ExamResults> examResults = resultsRepository.findAll();
        return resultsMapper.toExamResultsDtoList(examResults);
    }

    public ExamResultsDto getByIdExamResults(Integer id) throws NotFoundException {
        try {
            ExamResults examResults = resultsRepository.findById(id).orElse(null);
            return resultsMapper.toExamResultsDto(examResults);
        } catch (Exception e) {
            throw new NotFoundException("ExamResults not found id " + e.getMessage());
        }
    }

    public void updateExamResults(ExamResultsDto examResultsDto, Integer id) throws NotFoundException {
        try {
            Optional<ExamResults> optional = resultsRepository.findById(id);
            if (optional.isPresent()) {
                ExamResults oldExamResults = optional.get();
                ExamResults newExamResults = resultsMapper.toExamResults(examResultsDto);
                updateExamResultsFields(oldExamResults, newExamResults);
                resultsRepository.save(oldExamResults);
            }
        } catch (Exception e) {
            throw new NotFoundException("ExamResults not update" + e.getMessage());
        }

    }

    private void updateExamResultsFields(ExamResults oldExamResults, ExamResults newExamResults) {
        oldExamResults.setObtainedMarks(newExamResults.getObtainedMarks());
        oldExamResults.setTotalMarks(newExamResults.getTotalMarks());
        oldExamResults.setExamDate(newExamResults.getExamDate());
    }

    public void deleteExamResults(Integer id) throws NotFoundException {
        try {
            resultsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("ExamResults not delete" + e.getMessage());
        }

    }
}
