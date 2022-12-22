package com.pokemonreview.api.service;
import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.exception.PokemonNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImplementation implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired // put auto wired on the constructor. it's the official way and easier to unit test.
    public PokemonServiceImplementation(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        Pokemon newPokemon = pokemonRepository.save(pokemon);
        PokemonDTO pokemonResponse = new PokemonDTO();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());
        return pokemonResponse;
    }

    @Override
    public List<PokemonDTO> getAllPokemons() {
        // Pokemon pokemon33 = pokemonRepository.findById(74664).orElseThrow(() -> new PokemonNotFoundException("IMPORTANT MSG")); --> Just for testing
        List<Pokemon> pokemons = pokemonRepository.findAll();
        // we use map because it returns a new List.
        return pokemons.stream().map(pokemon -> mapToDTO(pokemon)).collect(Collectors.toList());
    }

    @Override
    public PokemonDTO getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("COULD NOT BE FOUND"));
        return mapToDTO(pokemon);
    }

    @Override
    public PokemonDTO updatePokemon(PokemonDTO pokemonDTO, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("COULD NOT BE UPDATED"));
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return mapToDTO(updatedPokemon);
    }

    // create our own mapper because in real world / enterprise prod env, mapping utilities aren't really used.
    // notice this one returns a DTO
    private PokemonDTO mapToDTO(Pokemon pokemon) {
        PokemonDTO pokemonDTO = new PokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }

    private Pokemon mapToEntity(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        return pokemon;
    }

    // 1:50 of Exception Handling--------------------


}



