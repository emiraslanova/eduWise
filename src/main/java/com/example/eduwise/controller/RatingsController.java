package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.RatingsDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.RatingsService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ratings")
public class RatingsController {

    private final RatingsService ratingsService;

    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @PostMapping
    public BaseResponse<RatingsDto> creatRatings(@Valid @RequestBody RatingsDto ratingsDto){
        try{
            return BaseResponse.ok(ratingsService.creatRatings(ratingsDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<RatingsDto> getAllRatings(){
        return ratingsService.getAllRatings();
    }
    @GetMapping("/{id}")
    public BaseResponse<RatingsDto> getByIdRatings(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(ratingsService.getByIdRatings(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateRatings(@Valid @RequestBody RatingsDto ratingsDto,@PathVariable Integer id) throws NotFoundException {
        ratingsService.updateRatings(ratingsDto ,id);
    }
    @DeleteMapping("/{id}")
    public  void  deleteRatings(@Valid @PathVariable Integer id) throws NotFoundException {
        ratingsService.deleteRatings(id);
    }
}
