package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.AnimalValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/koala")

public class KoalaController {
    private Map<Integer, Koala> koalas;
    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
        koalas.put(1,new Koala(1,"Koala", 80, Gender.FEMALE, 7));
    }

    @GetMapping("/")
    public List<Koala> get(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala get(@PathVariable int id){
        AnimalValidation.isIdValid(id);
        AnimalValidation.isIdNotExist(id,koalas);
        return koalas.get(id);
    }
    @PostMapping("/")
    public Koala save(@RequestBody Koala koala){
        AnimalValidation.isAnimalValid(koala);
        AnimalValidation.isIdExist(koala.getId(),koalas);
        AnimalValidation.isIdValid(koala.getId());
        koalas.put(koala.getId(),koala);
        return koala;
    }
    @PutMapping("/{id}")
    public Koala update(@PathVariable int id,@RequestBody Koala koala){
        AnimalValidation.isAnimalValid(koala);
        AnimalValidation.isIdNotExist(id,koalas);
        AnimalValidation.isIdValid(id);
        koala.setId(id);
        koalas.put(id,koala);
        return koala;
    }
    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id){
        AnimalValidation.isIdNotExist(id,koalas);
        AnimalValidation.isIdValid(id);
        Koala koala = koalas.get(id);
        koalas.remove(koala);
        return koala;
    }
}
