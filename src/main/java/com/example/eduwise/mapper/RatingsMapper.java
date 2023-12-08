package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.RatingsDto;
import com.example.eduwise.model.entity.Ratings;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingsMapper {
    Ratings toRatings(RatingsDto ratingsDto);

    RatingsDto toRatingsDto(Ratings savedRatings);

    List<RatingsDto> toRatingsDtoList(List<Ratings> ratingsList);
}
