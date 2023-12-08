package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.BlogMapper;
import com.example.eduwise.model.dto.BlogDto;
import com.example.eduwise.model.entity.Blog;
import com.example.eduwise.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }


    public BlogDto creatBlog(BlogDto blogDto) throws NotFoundException {
        try {
            Blog blog = blogMapper.toBlog(blogDto);
            Blog savedBlog = blogRepository.save(blog);
            return blogMapper.toBlogDto(savedBlog);
        } catch (Exception ex) {
            throw new NotFoundException("blog not created" + ex.getMessage());
        }

    }

    public List<BlogDto> getAllBlog() {
        List<Blog> blogs = blogRepository.findAll();
        return blogMapper.toBlogDtoList(blogs);
    }

    public BlogDto getByIdBlog(Integer id) throws NotFoundException {
        try {
            Blog blog = blogRepository.findById(id).orElse(null);
            return blogMapper.toBlogDto(blog);
        } catch (Exception ex) {
            throw new NotFoundException("Blog not found id" + ex.getMessage());
        }
    }

    public void updateBlog(BlogDto blogDto, Integer id) throws NotFoundException {
        try {
            Optional<Blog> optionalBlog = blogRepository.findById(id);
            if (optionalBlog.isPresent()) {
                Blog oldBlog = optionalBlog.get();
                Blog newBlog = blogMapper.toBlog(blogDto);
                updateBlogFields(oldBlog, newBlog);
                blogRepository.save(oldBlog);
            }
        } catch (Exception e) {
            throw new NotFoundException("Blog not update, id" + e.getMessage());
        }
    }

    private void updateBlogFields(Blog oldBlog, Blog newBlog) {
        oldBlog.setTitle(newBlog.getTitle());
        oldBlog.setContent(newBlog.getContent());
        oldBlog.setAuthorId(newBlog.getAuthorId());
        oldBlog.setPublishData(newBlog.getPublishData());

    }

    public void deleteBlog(Integer id) throws NotFoundException {
        try {
            blogRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("blog not delete id" + e.getMessage());
        }

    }
}
