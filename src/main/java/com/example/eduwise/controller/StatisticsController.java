package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.StatisticsDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.StatisticsService;
import jakarta.validation.Valid;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping
    public BaseResponse<StatisticsDto> creatStatistics(@Valid @RequestBody StatisticsDto statisticsDto){
        try {
            return BaseResponse.ok(statisticsService.creatStatistics(statisticsDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<StatisticsDto> getAllStatistics(){
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/{id}")
    public BaseResponse<StatisticsDto> getByIdStatistics(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(statisticsService.getByIdStatistics(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateStatistics(@Valid @RequestBody StatisticsDto statisticsDto,@PathVariable Integer id) throws NotFoundException {
        statisticsService.updateStatistics(statisticsDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteStatistics(@Valid @PathVariable Integer id) throws NotFoundException {
        statisticsService.deleteStatistics(id);
    }
}
