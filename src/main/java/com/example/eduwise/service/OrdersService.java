package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.OrdersMapper;
import com.example.eduwise.model.dto.OrdersDto;
import com.example.eduwise.model.entity.Orders;
import com.example.eduwise.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public OrdersService(OrdersRepository ordersRepository, OrdersMapper ordersMapper) {
        this.ordersRepository = ordersRepository;
        this.ordersMapper = ordersMapper;
    }

    public OrdersDto creatOrders(OrdersDto ordersDto) throws NotFoundException {
        try {
            Orders orders = ordersMapper.toOrders(ordersDto);
            Orders savedOrders = ordersRepository.save(orders);
            return ordersMapper.toOrdersDto(savedOrders);
        } catch (Exception e) {
            throw new NotFoundException("Orders not created" + e.getMessage());
        }
    }

    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersMapper.toOrdersDtoList(ordersList);
    }

    public OrdersDto getByIdOrders(Integer id) throws NotFoundException {
        try {
            Orders orders = ordersRepository.findById(id).orElse(null);
            return ordersMapper.toOrdersDto(orders);
        } catch (Exception e) {
            throw new NotFoundException("Orders not found id " + e.getMessage());
        }
    }

    public void updateOrders(OrdersDto ordersDto, Integer id) throws NotFoundException {
        Optional<Orders> optionalOrders = ordersRepository.findById(id);
        if (optionalOrders.isPresent()) {
            Orders oldOrders = optionalOrders.get();
            Orders newOrders = ordersMapper.toOrders(ordersDto);
            updateOrdersFields(oldOrders, newOrders);
            ordersRepository.save(oldOrders);
            throw new NotFoundException("Orders not update");
        }
    }

    private void updateOrdersFields(Orders oldOrders, Orders newOrders) {
        oldOrders.setOrderDate(newOrders.getOrderDate());
        oldOrders.setQuantity(newOrders.getQuantity());
        oldOrders.setTotalAmount(newOrders.getTotalAmount());
        oldOrders.setPaymentStatus(newOrders.getPaymentStatus());

    }

    public void deleteOrders(Integer id) throws NotFoundException {
        ordersRepository.deleteById(id);
        throw new NotFoundException("Orders not delete ");

    }
}
