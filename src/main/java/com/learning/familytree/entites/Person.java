package com.learning.familytree.entites;


import java.util.HashSet;
import java.util.Set;

public class Person {
    private Gender gender;
    private String name;
    private Set<Person> children;

    public Person(String name, Gender g) {
        this.name=name;
        this.gender=g;
        this.children=new HashSet<>();
    }

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public Set<Person> getChildren() {
        return children;
    }
}
