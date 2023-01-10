package com.pokemonreview.api.service;
import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.repository.ReviewRepository;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO);

    List<ReviewDTO> getReviewsByPokemonId(int id);

    ReviewDTO getReviewById(int reviewId, int pokemonId);

    ReviewDTO updateReview(int pokemonId, int reviewId, ReviewDTO reviewDTO);

    void deleteReview(int pokemonId, int reviewId);

}
