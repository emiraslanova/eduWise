package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.CategoryMapper;
import com.example.eduwise.model.dto.CategoryDto;
import com.example.eduwise.model.entity.Category;
import com.example.eduwise.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto creatCategory(CategoryDto categoryDto) throws NotFoundException {
        try {
            Category category = categoryMapper.toCategory(categoryDto);
            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.toCategoryDto(savedCategory);
        }catch (Exception e){
            throw new NotFoundException("Category not created" + e.getMessage());
        }
    }

    public List<CategoryDto> getAllCategory() {
        List<Category>categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDtoList(categories);
    }

    public CategoryDto getByIdCategory(Integer id) throws NotFoundException {
        try {
            Category category = categoryRepository.findById(id).orElse(null);
            return categoryMapper.toCategoryDto(category);
        }catch (Exception e){
            throw new NotFoundException("Category not found id " + e.getMessage());
        }
    }

    public void updateCategory( CategoryDto categoryDto ,Integer id) throws NotFoundException {
        try {
            Optional<Category>categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()){
                Category oldCategory = categoryOptional.get();
                Category newCategory = categoryMapper.toCategory(categoryDto);
                updateCategoryFields(oldCategory, newCategory);
                categoryRepository.save(oldCategory);
            }
        }catch (Exception e){
            throw new NotFoundException("Category not update id" + e.getMessage());
        }
    }
    private void updateCategoryFields(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        oldCategory.setDescription(newCategory.getDescription());
    }

    public void deleteCategory(Integer id) throws NotFoundException {
        try {
            categoryRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundException(" category not delete ,id" + e.getMessage());
        }

    }
}
