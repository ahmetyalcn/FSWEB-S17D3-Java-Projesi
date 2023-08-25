package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.AnimalErrorResponse;
import com.workintech.zoo.exceptions.AnimalException;
import com.workintech.zoo.exceptions.AnimalValidation;
import com.workintech.zoo.exceptions.RestException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroo")

public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;
    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
        kangaroos.put(1,new Kangaroo(1,"Kangaroo", 80, Gender.FEMALE, true,1));
    }

    @GetMapping("/")
    public List<Kangaroo> get(){
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Kangaroo get(@PathVariable int id){
        AnimalValidation.isIdValid(id);
        AnimalValidation.isIdNotExist(id,kangaroos);
        return kangaroos.get(id);
    }
    @PostMapping("/")
    public Kangaroo save(@RequestBody Kangaroo kangaroo){
       AnimalValidation.isAnimalValid(kangaroo);
       AnimalValidation.isIdExist(kangaroo.getId(),kangaroos);
       AnimalValidation.isIdValid(kangaroo.getId());
       kangaroos.put(kangaroo.getId(),kangaroo);
       return kangaroo;
    }
    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id,@RequestBody Kangaroo kangaroo){
        AnimalValidation.isAnimalValid(kangaroo);
        AnimalValidation.isIdNotExist(id,kangaroos);
        AnimalValidation.isIdValid(id);
        kangaroo.setId(id);
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        AnimalValidation.isIdNotExist(id,kangaroos);
        AnimalValidation.isIdValid(id);
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(kangaroo);
        return kangaroo;
    }
}
