package com.example.demo.repository;

import com.example.demo.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CharacterRepository extends JpaRepository<Character,Long> {
    @Query("Select c from Character c where c.name like %:name%")
    List<Character> findCharactersByNameIsLike(String name);
    @Query(value = "select * from character limit 1 offset floor(random() * (select count(*) from character ))",nativeQuery = true)
    List<Character> getRandom();
    Character findByName(String name);
}
