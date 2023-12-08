package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.BasketDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.BasketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public BaseResponse<BasketDto> creatBasket(@Valid @RequestBody BasketDto basketDto){
        try {
            return BaseResponse .ok(basketService.createdBasket(basketDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<BasketDto> getAllBasket(){
        return basketService.getAllBasket();
    }
    @GetMapping("/{id}")
    public BaseResponse<BasketDto> getByIdBasket(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(basketService.getByIdBasket(id));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void  updateBasket(@Valid @RequestBody BasketDto basketDto,@PathVariable Integer id) throws NotFoundException {
        basketService.updateBasket(basketDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteBasket(@Valid @PathVariable Integer id) throws NotFoundException {
        basketService.deleteBasket(id);
    }
}
