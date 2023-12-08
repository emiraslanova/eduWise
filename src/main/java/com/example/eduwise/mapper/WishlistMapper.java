package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.WishlistDto;
import com.example.eduwise.model.entity.Wishlist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
    Wishlist toWishlist(WishlistDto wishlistDto);

    WishlistDto toWishlistDto(Wishlist savedWishlist);

    List<WishlistDto> toWishlistDtoList(List<Wishlist> wishlists);
}
