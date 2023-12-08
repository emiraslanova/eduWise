package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.RatingsMapper;
import com.example.eduwise.model.dto.RatingsDto;
import com.example.eduwise.model.entity.Ratings;
import com.example.eduwise.repository.RatingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingsService {

    private final RatingsRepository repository;
    private final RatingsMapper mapper;

    public RatingsService(RatingsRepository repository, RatingsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RatingsDto creatRatings(RatingsDto ratingsDto) throws NotFoundException {
        try {
            Ratings ratings = mapper.toRatings(ratingsDto);
            Ratings savedRatings = repository.save(ratings);
            return mapper.toRatingsDto(savedRatings);
        } catch (Exception ex) {
            throw new NotFoundException("Ratings not created " + ex.getMessage());
        }
    }

    public List<RatingsDto> getAllRatings() {
        List<Ratings> ratingsList = repository.findAll();
        return mapper.toRatingsDtoList(ratingsList);
    }

    public RatingsDto getByIdRatings(Integer id) throws NotFoundException {
        try {
            Ratings ratings = repository.findById(id).orElse(null);
            return mapper.toRatingsDto(ratings);
        } catch (Exception ex) {
            throw new NotFoundException("Ratings not found id " + ex.getMessage());
        }
    }

    public void updateRatings(RatingsDto ratingsDto, Integer id) throws NotFoundException {
        Optional<Ratings> optional = repository.findById(id);
        if (optional.isPresent()) {
            Ratings oldRatings = optional.get();
            Ratings newRatings = mapper.toRatings(ratingsDto);
            updateRatingsFields(oldRatings, newRatings);
            repository.save(oldRatings);
            throw new NotFoundException(" Ratings not update");
        }

    }

    private void updateRatingsFields(Ratings oldRatings, Ratings newRatings) {
        oldRatings.setRating(newRatings.getRating());
        oldRatings.setReview(newRatings.getReview());
    }

    public void deleteRatings(Integer id) throws NotFoundException {
        repository.deleteById(id);
        throw new NotFoundException("Ratings not delete");

    }
}
