package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.BlogDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public BaseResponse<BlogDto> creatBlog(@Valid @RequestBody BlogDto blogDto){
        try {
            return BaseResponse.ok(blogService.creatBlog(blogDto));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    private List<BlogDto> getAllBlog() {
        return blogService.getAllBlog();
    }
    @GetMapping("/{id}")
    public BaseResponse<BlogDto> getByIdBlog(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(blogService.getByIdBlog(id));
        }catch (Exception e){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateBlog(@Valid @RequestBody BlogDto blogDto,@PathVariable Integer id) throws NotFoundException {
        blogService.updateBlog(blogDto,id);
    }
    @DeleteMapping("/{id}")
    public void  deleteBlog(@Valid @PathVariable Integer id) throws NotFoundException {
        blogService.deleteBlog(id);
    }
}
