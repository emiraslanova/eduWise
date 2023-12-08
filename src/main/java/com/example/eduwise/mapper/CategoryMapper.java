package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.CategoryDto;
import com.example.eduwise.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category savedCategory);

    List<CategoryDto> toCategoryDtoList(List<Category> categories);
}
