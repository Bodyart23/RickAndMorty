package com.example.demo.service;

import com.example.demo.repository.CharacterRepository;
import com.example.demo.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getRandomCharacter(){
        List<Character> characters = characterRepository.getRandom();
        return characters;
    }

    public List<Character> getByName(String name){
        List<Character> characters = characterRepository.findCharactersByNameIsLike(name);
        return characters;
    }
}
