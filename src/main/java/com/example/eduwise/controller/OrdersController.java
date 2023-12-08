package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.OrdersDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public BaseResponse<OrdersDto> creatOrders(@Valid @RequestBody OrdersDto ordersDto){
        try {
            return BaseResponse.ok(ordersService.creatOrders(ordersDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<OrdersDto> getAllOrders(){
        return ordersService.getAllOrders();
    }
    @GetMapping("/{id}")
    public BaseResponse<OrdersDto> getByIdOrders(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(ordersService.getByIdOrders(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateOrders(@Valid @RequestBody OrdersDto ordersDto , @PathVariable Integer id) throws NotFoundException {
        ordersService.updateOrders(ordersDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteOrders(@Valid @PathVariable Integer id) throws NotFoundException {
        ordersService.deleteOrders(id);
    }
}
