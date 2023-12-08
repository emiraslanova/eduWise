package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.StatisticsDto;
import com.example.eduwise.model.entity.Statistics;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {
    Statistics toStatistics(StatisticsDto statisticsDto);

    StatisticsDto toStatisticsDto(Statistics savedStatistics);

    List<StatisticsDto> toStatisticsDtoList(List<Statistics> statisticsList);
}
