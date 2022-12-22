package com.pokemonreview.api.controller;
import com.pokemonreview.api.dto.PokemonDTO;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<List<PokemonDTO>> getPokemon() {
        return new ResponseEntity<>(pokemonService.getAllPokemons(), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDTO> pokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDTO), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int pokemonId) {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) {
        System.out.println(pokemonId);
        return ResponseEntity.ok("nuked!");
    }
}
