package com.pokemonreview.api.service;
import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.dto.PokemonResponse;

import java.util.List;



public interface PokemonService {
    PokemonDTO createPokemon(PokemonDTO pokemonDTO);

    // get all pokemons will return a List (the facebook feed)
    PokemonResponse getAllPokemons(int pageNo, int pageSize);

    // get by ID --> "the detail endpoint" (the individual post you click on)
    PokemonDTO getPokemonById(int id);

    // update a pokemon
    PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id);

    void deletePokemon(int id);
}

