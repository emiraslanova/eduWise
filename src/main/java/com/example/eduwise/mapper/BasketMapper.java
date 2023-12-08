package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.BasketDto;
import com.example.eduwise.model.entity.Basket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketMapper  {

    Basket toBasket(BasketDto basketDto);

    BasketDto toBasketDto(Basket savedBasket);

    List<BasketDto> toBasketDtoList(List<Basket> baskets);
}
