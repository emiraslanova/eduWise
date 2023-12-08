package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.CategoryDto;
import com.example.eduwise.model.entity.Category;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public BaseResponse<CategoryDto> creatCategory(@Valid @RequestBody CategoryDto categoryDto){
        try{
            return BaseResponse.ok(categoryService.creatCategory(categoryDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/{id}")
    public BaseResponse<CategoryDto>getByIdCategory(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(categoryService.getByIdCategory(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id) throws NotFoundException {
        categoryService.updateCategory(categoryDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@Valid @PathVariable Integer id) throws NotFoundException {
        categoryService.deleteCategory(id);
    }
}
