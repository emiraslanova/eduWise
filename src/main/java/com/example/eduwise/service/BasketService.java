package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.exceptions.UserException;
import com.example.eduwise.mapper.BasketMapper;
import com.example.eduwise.model.dto.BasketDto;
import com.example.eduwise.model.entity.Basket;
import com.example.eduwise.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    public BasketService(BasketRepository basketRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }


    public BasketDto createdBasket(BasketDto basketDto) throws NotFoundException {
        try {
            Basket basket = basketMapper.toBasket(basketDto);
            Basket savedBasket = basketRepository.save(basket);
            return basketMapper.toBasketDto(savedBasket);
        } catch (Exception ex) {
            throw new NotFoundException("Basket not created" + ex.getMessage());
        }
    }

    public List<BasketDto> getAllBasket() {
        List<Basket> baskets = basketRepository.findAll();
        return basketMapper.toBasketDtoList(baskets);
    }

    public BasketDto getByIdBasket(Integer id) throws NotFoundException {
        try {
            Basket basket = basketRepository.findById(id).orElse(null);
            return basketMapper.toBasketDto(basket);
        } catch (Exception ex) {
            throw new NotFoundException("Basket not fond id " + ex.getMessage());
        }

    }

    public void updateBasket(BasketDto basketDto, Integer id) throws NotFoundException {
        try {
            Optional<Basket> optionalBasket = basketRepository.findById(id);
            if (optionalBasket.isPresent()) {
                Basket oldBasket = optionalBasket.get();
                Basket newBasket = basketMapper.toBasket(basketDto);
                updateBasketFields(oldBasket, newBasket);
                basketRepository.save(oldBasket);
            }
        } catch (Exception ex) {
            throw new NotFoundException("basket not update id " + ex.getMessage());
        }
    }

    private void updateBasketFields(Basket oldBasket, Basket newBasket) {
        oldBasket.setCount(newBasket.getCount());
        oldBasket.setTotalAmount(newBasket.getTotalAmount());
        oldBasket.setPrice(newBasket.getPrice());

    }

    public void deleteBasket(Integer id) throws NotFoundException {
        try {
            basketRepository.deleteById(id);
        } catch (Exception ex) {
            throw new NotFoundException("Basket not delete id " + ex.getMessage());
        }

    }
}
