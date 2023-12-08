package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.ExamMapper;
import com.example.eduwise.model.dto.ExamDto;
import com.example.eduwise.model.entity.Exam;
import com.example.eduwise.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public ExamService(ExamRepository examRepository, ExamMapper examMapper) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
    }

    public ExamDto creatExam(ExamDto examDto) throws NotFoundException {
        try {
            Exam exam = examMapper.toExam(examDto);
            Exam savedExam = examRepository.save(exam);
            return examMapper.toExamDto(savedExam);
        } catch (Exception e) {
            throw new NotFoundException("Exam not created" + e.getMessage());
        }

    }

    public List<ExamDto> getAllExam() {
        List<Exam> exams = examRepository.findAll();
        return examMapper.toExamDtoList(exams);
    }

    public ExamDto getByIdExam(Integer id) throws NotFoundException {
        try {
            Exam exam = examRepository.findById(id).orElse(null);
            return examMapper.toExamDto(exam);
        } catch (Exception e) {
            throw new NotFoundException("Exam not found id " + e.getMessage());
        }

    }

    public void updateExam(ExamDto examDto, Integer id) throws NotFoundException {
        try {
            Optional<Exam> optionalExam = examRepository.findById(id);
            if (optionalExam.isPresent()) {
                Exam oldExam = optionalExam.get();
                Exam newExam = examMapper.toExam(examDto);
                updateExamFields(oldExam, newExam);
                examRepository.save(oldExam);
            }
        } catch (Exception e) {
            throw new NotFoundException("Exam not update " + e.getMessage());
        }

    }

    private void updateExamFields(Exam oldExam, Exam newExam) {
        oldExam.setExamName(newExam.getExamName());
        oldExam.setExamDate(newExam.getExamDate());
        oldExam.setDurationMinutes(newExam.getDurationMinutes());
        oldExam.setTotalMarks(newExam.getTotalMarks());
        oldExam.setDescription(newExam.getDescription());

    }

    public void deleteExam(Integer id) throws NotFoundException {
        try {
            examRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("exam not delete id" + e.getMessage());
        }

    }
}
