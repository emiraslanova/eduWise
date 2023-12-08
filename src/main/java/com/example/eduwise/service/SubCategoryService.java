package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.SubCategoryMapper;
import com.example.eduwise.model.dto.SubCategoryDto;
import com.example.eduwise.model.entity.SubCategory;
import com.example.eduwise.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    private final SubCategoryRepository repository;
    private final SubCategoryMapper mapper;

    public SubCategoryService(SubCategoryRepository repository, SubCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SubCategoryDto creatSubCategory(SubCategoryDto subCategoryDto) throws NotFoundException {
        try {
            SubCategory subCategory = mapper.toSubCategory(subCategoryDto);
            SubCategory savedSubCategory = repository.save(subCategory);
            return mapper.toSubCategoryDto(savedSubCategory);
        } catch (Exception e) {
            throw new NotFoundException("SubCategory not created" + e.getMessage());
        }

    }

    public List<SubCategoryDto> getAllSubCategory() {
        List<SubCategory> subCategoryList = repository.findAll();
        return mapper.toSubCategoryDtoList(subCategoryList);
    }

    public SubCategoryDto getByIdSubCategory(Integer id) throws NotFoundException {
        try {
            SubCategory subCategory = repository.findById(id).orElse(null);
            return mapper.toSubCategoryDto(subCategory);
        } catch (Exception e) {
            throw new NotFoundException("SubCategory not found " + e.getMessage());
        }
    }

    public void updateSubCategory(SubCategoryDto subCategoryDto, Integer id) throws NotFoundException {
        Optional<SubCategory> optional = repository.findById(id);
        if (optional.isPresent()) {
            SubCategory oldSubCategory = optional.get();
            SubCategory newSubCategory = mapper.toSubCategory(subCategoryDto);
            updateSubCategoryFields(oldSubCategory, newSubCategory);
            repository.save(oldSubCategory);
            throw new NotFoundException("SubCategory not update");
        }

    }

    private void updateSubCategoryFields(SubCategory oldSubCategory, SubCategory newSubCategory) {
        oldSubCategory.setSubCategoryName(newSubCategory.getSubCategoryName());
        oldSubCategory.setDescription(newSubCategory.getDescription());
    }

    public void deleteSubCategory(Integer id) throws NotFoundException {
        repository.deleteById(id);
        throw new NotFoundException("SubCategory not delete ");

    }
}
