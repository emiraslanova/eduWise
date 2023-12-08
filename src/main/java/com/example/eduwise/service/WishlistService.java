package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.WishlistMapper;
import com.example.eduwise.model.dto.WishlistDto;
import com.example.eduwise.model.entity.Wishlist;
import com.example.eduwise.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private  final WishlistRepository repository;
    private  final WishlistMapper mapper;

    public WishlistService(WishlistRepository repository, WishlistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public WishlistDto creatWishlist(WishlistDto wishlistDto) throws NotFoundException {
        try {
            Wishlist wishlist = mapper.toWishlist(wishlistDto);
            Wishlist savedWishlist = repository.save(wishlist);
            return mapper.toWishlistDto(savedWishlist);
        }catch (Exception e){
            throw new NotFoundException("Wishlist not created");
        }

    }

    public List<WishlistDto> getAllWishlist() {
        List<Wishlist> wishlists = repository.findAll();
        return mapper.toWishlistDtoList(wishlists);
    }

    public WishlistDto getByIdWishlist(Integer id) throws NotFoundException {
        try {
            Wishlist wishlist = repository.findById(id).orElse(null);
            return mapper.toWishlistDto(wishlist);
        }catch (Exception e){
            throw new NotFoundException("Wishlist not found id" + e.getMessage());
        }
    }

    public void updateWishlist(WishlistDto wishlistDto, Integer id) throws NotFoundException {
        Optional<Wishlist> optional = repository.findById(id);
        if (optional.isPresent()){
            Wishlist oldWishlist = optional.get();
            Wishlist newWishlist = mapper.toWishlist(wishlistDto);
            updateWishlistFields(oldWishlist,newWishlist);
            repository.save(oldWishlist);
        }throw new NotFoundException("Wishlist not update");

    }

    private void updateWishlistFields(Wishlist oldWishlist, Wishlist newWishlist) {
        oldWishlist.setPrice(newWishlist.getPrice());
    }

    public void deleteWishlist(Integer id) throws NotFoundException {
        repository.deleteById(id);
        throw new NotFoundException("Wishlist not delete");
    }
}
