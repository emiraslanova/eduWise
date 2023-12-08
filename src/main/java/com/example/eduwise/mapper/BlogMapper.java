package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.BlogDto;
import com.example.eduwise.model.entity.Blog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    Blog toBlog(BlogDto blogDto);

    BlogDto toBlogDto(Blog blog);

    List<BlogDto> toBlogDtoList(List<Blog> blogs);
}
