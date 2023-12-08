package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.StatisticsMapper;
import com.example.eduwise.model.dto.StatisticsDto;
import com.example.eduwise.model.entity.Statistics;
import com.example.eduwise.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    private final StatisticsRepository repository;
    private final StatisticsMapper mapper;

    public StatisticsService(StatisticsRepository repository, StatisticsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StatisticsDto creatStatistics(StatisticsDto statisticsDto) throws NotFoundException {
        try {
            Statistics statistics = mapper.toStatistics(statisticsDto);
            Statistics savedStatistics = repository.save(statistics);
            return mapper.toStatisticsDto(savedStatistics);
        } catch (Exception e) {
            throw new NotFoundException("Statistics not created" + e.getMessage());
        }
    }

    public List<StatisticsDto> getAllStatistics() {
        List<Statistics> statisticsList = repository.findAll();
        return mapper.toStatisticsDtoList(statisticsList);
    }

    public StatisticsDto getByIdStatistics(Integer id) throws NotFoundException {
        try {
            Statistics statistics = repository.findById(id).orElse(null);
            return mapper.toStatisticsDto(statistics);
        } catch (Exception e) {
            throw new NotFoundException("Statistics not found id " + e.getMessage());
        }
    }

    public void updateStatistics(StatisticsDto statisticsDto, Integer id) throws NotFoundException {
        Optional<Statistics> optional = repository.findById(id);
        if (optional.isPresent()) {
            Statistics oldStatistics = optional.get();
            Statistics newStatistics = mapper.toStatistics(statisticsDto);
            updateStatisticsFields(oldStatistics, newStatistics);
            repository.save(oldStatistics);
            throw new NotFoundException("Statistics not update");
        }
    }

    private void updateStatisticsFields(Statistics oldStatistics, Statistics newStatistics) {
        oldStatistics.setName(newStatistics.getName());
        oldStatistics.setDescription(newStatistics.getDescription());
        oldStatistics.setTotalBudget(newStatistics.getTotalBudget());
    }

    public void deleteStatistics(Integer id) throws NotFoundException {
        repository.deleteById(id);
        throw new NotFoundException("Statistics not delete");
    }
}
