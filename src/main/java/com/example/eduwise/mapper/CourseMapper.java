package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.CourseDto;
import com.example.eduwise.model.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper  {
    Course toCourse(CourseDto courseDto);

    CourseDto toCourseDto(Course savedCourse);

    List<CourseDto> toCourseDtoList(List<Course> courses);
}
