package com.pokemonreview.api.dto;
import lombok.Data;


@Data
public class ReviewDTO {
    private int id;
    private String title;
    private String content;
    private int stars;

}
