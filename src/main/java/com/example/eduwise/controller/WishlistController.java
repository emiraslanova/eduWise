package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.WishlistDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/wishlist")
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @PostMapping
    public BaseResponse<WishlistDto> creatWishlist(@Valid @RequestBody WishlistDto wishlistDto) {
        try {
            return BaseResponse.ok(service.creatWishlist(wishlistDto));
        } catch (Exception ex) {
            return BaseResponse.fail();
        }
    }

    @GetMapping
    public List<WishlistDto> getAllWishlist() {
        return service.getAllWishlist();
    }

    @GetMapping("/{id}")
    BaseResponse<WishlistDto> getByIdWishlist(@Valid @PathVariable Integer id) {
        try {
            return BaseResponse.success(service.getByIdWishlist(id));
        } catch (Exception ex) {
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public  void updateWishlist(@Valid @RequestBody WishlistDto wishlistDto,@PathVariable Integer id) throws NotFoundException {
        service.updateWishlist(wishlistDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteWishlist(@Valid @PathVariable Integer id) throws NotFoundException {
        service.deleteWishlist(id);
    }

}
