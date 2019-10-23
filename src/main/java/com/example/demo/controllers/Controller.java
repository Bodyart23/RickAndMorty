package com.example.demo.controllers;

import com.example.demo.model.Character;
import com.example.demo.service.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "The Rick and Morty API", description = "Methods for character selection")
public class Controller {
    @Autowired
    public CharacterService characterService;

    @ApiOperation(value = "Choose a random character")
    @GetMapping("/characters/random/")
    public List<Character> randomCharacter() {
        List<Character> characters = characterService.getRandomCharacter();
        return characters;
    }

    @ApiOperation(value = "Search by name")
    @GetMapping("/characters/serch/{name}")
    public List<Character> serchByName(@PathVariable(name = "name") String name) {
        List<Character> characters = characterService.getByName(name);
        return characters;
    }

    @GetMapping("/character/{id}")
    public Optional<Character> serchByID(@PathVariable(value = "id") Long id){
        Optional<Character> character = characterService.getCharacter(id);
        return character;
    }
}
