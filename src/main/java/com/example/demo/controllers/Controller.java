package com.example.demo.controllers;

import com.example.demo.model.Character;
import com.example.demo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    public CharacterService characterService;

    @GetMapping("/characters/random/")
    public List<Character> randomCharacter() {
        List<Character> characters = characterService.getRandomCharacter();
        return characters;
    }

    @GetMapping("/characters/serch/{name}")
    public List<Character> serchByName(@PathVariable(name = "name") String name) {
        List<Character> characters = characterService.getByName(name);
        return characters;
    }
}
