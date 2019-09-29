package com.learning.familytree;

import com.learning.familytree.annotations.CommandRegex;
import com.learning.familytree.entites.Person;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FamilyTree {

    Map<String, Person> personMap;

    public FamilyTree() {
        personMap = new HashMap<>();
    }


    @CommandRegex("family-tree\\s+add\\s+person\\s+([\\w\\s]+)\\s+([M|F])\\s*")
    public Person addPerson(String name, String gender) {
        Person person =personMap.get(name);
        if(person==null) {
            System.out.println("adding person "+name);
            person = new Person(name, gender.equals("M")? Person.Gender.MALE : Person.Gender.FEMALE);
            personMap.put(name,person);
        }
        return person;
    }

//    @CommandRegex("family-tree\\s+add\\s+relationship\\s+(\\w+)\\s*")
//    public void addRelationship(String relationship) {
//    }

    @CommandRegex("family-tree\\s+connect\\s+([\\w\\s]+)\\s+as\\s+([\\w\\s]+)\\s+of\\s+([\\w\\s]+)\\s*")
    public void connect(String name1, String relationship, String name2) {
        if(relationship.equals("son")) {
            Person son = addPerson(name1, "M");
            Person parent = addPerson(name2, "M");
            parent.getChildren().add(son);
        }
        else if (relationship.equals("daughter")) {
            Person daughter = addPerson(name1, "F");
            Person parent = addPerson(name2, "M");
            parent.getChildren().add(daughter);
        }
    }

    @CommandRegex("family-tree\\s+count\\s+(\\w+)s\\s+of\\s+([\\w\\s]+)\\s*")
    public void count(String relationship, String name) {

        System.out.println(recCount(relationship.equals("son")? Person.Gender.MALE: Person.Gender.FEMALE, personMap.get(name)));
    }

    @CommandRegex("family-tree\\s+count\\s+all\\s+([\\w\\s]+)s\\s+of\\s+([\\w\\s]+)\\s*")
    public void countAll(String relationship, String name) {
        System.out.println(recCountAll(relationship.equals("son")? Person.Gender.MALE: Person.Gender.FEMALE, personMap.get(name)));
    }

    private int recCount(Person.Gender g, Person person) {
        int count=0;
        for (Person child: person.getChildren()) {
            if(child.getGender()==g) {
                count++;
            }
        }

        return count;
    }

    private int recCountAll(Person.Gender g, Person person) {
        int count=0;
        for (Person child: person.getChildren()) {
            if(child.getGender()==g) {
                count++;
            }
            count+=recCount(g,child);
        }

        return count;
    }


}
