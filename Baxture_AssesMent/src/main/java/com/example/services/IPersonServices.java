package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.entites.Person;


public interface IPersonServices {
   public List<Person> getallpersons();
   public Optional<Person> getpersonbyid(long id);
   public Person AddPerson(Person person);
   public Person Updatedetail(long id,Person person);
   public boolean DeletePerson(long id);
   
}
