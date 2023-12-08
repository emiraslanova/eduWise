package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.CourseMapper;
import com.example.eduwise.model.dto.CourseDto;
import com.example.eduwise.model.entity.Course;
import com.example.eduwise.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDto creatCourse(CourseDto courseDto) throws NotFoundException {
        try {
            Course course = courseMapper.toCourse(courseDto);
            Course savedCourse = courseRepository.save(course);
            return courseMapper.toCourseDto(savedCourse);
        } catch (Exception ex) {
            throw new NotFoundException("Course not created " + ex.getMessage());
        }

    }

    public List<CourseDto> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toCourseDtoList(courses);
    }

    public CourseDto getByIdCourse(Integer id) throws NotFoundException {
        try {
            Course course = courseRepository.findById(id).orElse(null);
            return courseMapper.toCourseDto(course);
        } catch (Exception ex) {
            throw new NotFoundException("Course not found id" + ex.getMessage());
        }

    }

    public void updateCourse(CourseDto courseDto, Integer id) throws NotFoundException {
        try {
            Optional<Course> optionalCourse = courseRepository.findById(id);
            if (optionalCourse.isPresent()) {
                Course oldCourse = optionalCourse.get();
                Course newCourse = courseMapper.toCourse(courseDto);
                updateCourseFields(oldCourse, newCourse);
                courseRepository.save(oldCourse);
            }
        } catch (Exception ex) {
            throw new NotFoundException("Course not update " + ex.getMessage());
        }

    }

    private void updateCourseFields(Course oldCourse, Course newCourse) {
        oldCourse.setTitle(newCourse.getTitle());
        oldCourse.setSubTitle(newCourse.getSubTitle());
        oldCourse.setPrice(newCourse.getPrice());
        oldCourse.setCoverImage(newCourse.getCoverImage());
        oldCourse.setDiscount(newCourse.getDiscount());
        oldCourse.setBestseller(newCourse.getBestseller());
        oldCourse.setRatings(newCourse.getRatings());
        oldCourse.setRatingCount(newCourse.getRatingCount());
        oldCourse.setInstructorId(newCourse.getInstructorId());
        oldCourse.setPurchasesCount(newCourse.getPurchasesCount());
        oldCourse.setCourseLink(newCourse.getCourseLink());
        oldCourse.setDuration(newCourse.getDuration());
        oldCourse.setInstructor(newCourse.getInstructor());
        oldCourse.setInWishlist(newCourse.getInWishlist());
        oldCourse.setDiscountPrice(newCourse.getDiscountPrice());
        oldCourse.setPriceMonthly(newCourse.getPriceMonthly());
        oldCourse.setPriceQuarterly(newCourse.getPriceQuarterly());
        oldCourse.setPriceSemiannually(newCourse.getPriceSemiannually());
        oldCourse.setPriceAnnually(newCourse.getPriceAnnually());
    }

    public void deleteCourse(Integer id) throws NotFoundException {
        try {
            courseRepository.deleteById(id);
        } catch (Exception ex) {
            throw new NotFoundException("course not delete id" + ex.getMessage());
        }

    }
}
