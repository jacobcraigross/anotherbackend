package com.pokemonreview.api.service;
import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.ReviewDTO;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.exception.ReviewNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewServiceImplementation implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDTO createReview(int pokemonId, ReviewDTO reviewDTO) {
        Review review = mapToEntity(reviewDTO);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("not found"));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDTO> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("fot found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("fot found"));

        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("this review doesnt belong to a pokemon");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDTO updateReview(int pokemonId, int reviewId, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("fot found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("fot found"));

        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("this review doesnt belong to a pokemon");
        }

        review.setTitle(reviewDTO.getTitle());
        review.setContent(reviewDTO.getContent());
        review.setStars(reviewDTO.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("fot found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("fot found"));

        if (review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("this review doesnt belong to a pokemon");
        }

        reviewRepository.delete(review);

    }

    private ReviewDTO mapToDto(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setStars(review.getStars());
        return reviewDTO;
    }

    private Review mapToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setContent(reviewDTO.getContent());
        review.setTitle(reviewDTO.getTitle());
        review.setStars(reviewDTO.getStars());
        return review;
    }
}
