package com.workintech.zoo.exceptions;

import com.workintech.zoo.controller.KangarooController;
import com.workintech.zoo.entity.Animal;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class AnimalValidation {
    public static void isIdValid(int id){
        if (id<=0){
            throw new AnimalException("invalid id: "+id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isIdExist(int id, Map animals){
        if (animals.containsKey(id)){
            throw new AnimalException("id already exist: "+id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isIdNotExist(int id, Map animals){
        if (!animals.containsKey(id)){
            throw new AnimalException("id not found: "+id, HttpStatus.BAD_REQUEST);
        }
    }
    public static void isAnimalValid(Animal animal){
        if (animal.getName()==null || animal.getName().isEmpty() || animal.getWeight()<=0 || animal.getWeight()>=100){
            throw new AnimalException("animal information is not correct", HttpStatus.BAD_REQUEST);
        }
    }
}
