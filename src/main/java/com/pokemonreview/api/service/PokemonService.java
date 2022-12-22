package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    // get all pokemons will return a List (the facebook feed)
    List<PokemonDTO> getAllPokemons();

    // get by ID --> "the detail endpoint" (the individual post you click on)
    PokemonDTO getPokemonById(int id);

    // update a pokemon
    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);

    // next video DELETE -------------
}
