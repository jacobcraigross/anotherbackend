package com.pokemonreview.api.controller;
import com.pokemonreview.api.model.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemon() {
        List<Pokemon> pokemon = new ArrayList<>();
        pokemon.add(new Pokemon(1, "KL", "Dirt"));
        pokemon.add(new Pokemon(2, "LK", "Mud"));
        pokemon.add(new Pokemon(3, "OP", "Slop"));
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/pokemon/{id}")
    public Pokemon pokemonDetail(@PathVariable int id) {
        return new Pokemon(id, "SD", "runoff");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
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
