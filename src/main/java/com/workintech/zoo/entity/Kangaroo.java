package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Kangaroo extends Animal{
    private boolean isAggressive;
    private double height;

    public Kangaroo(int id, String name, double weight, Gender gender, boolean isAggressive, double height) {
        super(id, name, weight, gender);
        this.isAggressive = isAggressive;
        this.height = height;
    }
}
