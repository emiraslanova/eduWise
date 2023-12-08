package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.SubCategoryDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subCategory")
public class SubCategoryController {

    private final SubCategoryService service;

    public SubCategoryController(SubCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public BaseResponse<SubCategoryDto> creatSubCategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
        try{
            return BaseResponse.ok(service.creatSubCategory(subCategoryDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<SubCategoryDto> getAllSubCategory(){
        return service.getAllSubCategory();
    }

    @GetMapping("/{id}")
    public BaseResponse<SubCategoryDto> getByIdSubCategory(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(service.getByIdSubCategory(id));
        }catch (Exception e){
           return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateSubCategory(@Valid @RequestBody SubCategoryDto subCategoryDto, @PathVariable Integer id) throws NotFoundException {
        service.updateSubCategory(subCategoryDto,id);
    }
    @DeleteMapping("/{id}")
    public void deleteSubCategory(@Valid @PathVariable Integer id) throws NotFoundException {
        service.deleteSubCategory(id);
    }
}
