package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.CourseDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public BaseResponse<CourseDto> creatCourse(@Valid @RequestBody CourseDto courseDto){
        try {
            return BaseResponse.ok(courseService.creatCourse(courseDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<CourseDto> getAllCourse(){
            return courseService.getAllCourse();

    }
    @GetMapping("/{id}")
    public BaseResponse<CourseDto> getByIdCourse(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(courseService.getByIdCourse(id));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateCourse(@Valid @RequestBody CourseDto courseDto, @PathVariable Integer id) throws NotFoundException {
        courseService.updateCourse(courseDto ,id);

    }
    @DeleteMapping("/{id}")
    public void deleteCourse(@Valid @PathVariable Integer id) throws NotFoundException {
        courseService.deleteCourse(id);
    }
}
