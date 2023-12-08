package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.SubCategoryDto;
import com.example.eduwise.model.entity.SubCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {
    SubCategory toSubCategory(SubCategoryDto subCategoryDto);

    SubCategoryDto toSubCategoryDto(SubCategory savedSubCategory);

    List<SubCategoryDto> toSubCategoryDtoList(List<SubCategory> subCategoryList);
}
