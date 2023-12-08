package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.OrdersDto;
import com.example.eduwise.model.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {
    Orders toOrders(OrdersDto ordersDto);

    OrdersDto toOrdersDto(Orders savedOrders);

    List<OrdersDto> toOrdersDtoList(List<Orders> ordersList);
}
