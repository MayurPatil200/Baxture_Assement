package com.example.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entites.Person;

import com.example.excepations.PersonNotFoundException;
import com.example.services.IPersonServices;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/person") // Fixed typo here
public class PersonController {
     
    @Autowired
    public IPersonServices iPersonServices;
    

    
    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson() {
        List<Person> mylist = iPersonServices.getallpersons();
        if (mylist.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if list is empty
        } else {
            return ResponseEntity.ok(mylist);
        }
    }
    
    @GetMapping("/{PersonId}")
    public ResponseEntity getPersonById(@PathVariable("PersonId") String userId) {
    	long id=0;
        try {
             id=Long.parseLong(userId);
            Optional<Person> person = iPersonServices.getpersonbyid(id);
            return ResponseEntity.ok(person);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid id provided");
        } catch (PersonNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity Addperson(@RequestBody Person person)
    
    {
    	if (person.getUsername() == null || person.getAge() == 0 || person.getHobbies().isEmpty()) 
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Required fields are missing");
    
    	Person addperson=  iPersonServices.AddPerson(person);
    	   if(addperson == null)
    	   {
    		   return null;
    	   }
    	   else
    		   return ResponseEntity.status(HttpStatus.CREATED).body(addperson);
    	  
    }
   
    @PutMapping("/update/{id}")
    public ResponseEntity UpdatePerson(@PathVariable("id") String personid,@RequestBody Person person)
    {
    	 long id =0;	 
    	try
    	{
    		id = Long.parseLong(personid);	
    	 iPersonServices.Updatedetail(id, person);
    	 return ResponseEntity.ok(person);
       }
    	catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid id provided");
    	}
    	catch (PersonNotFoundException e) {
    		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
    }


   @DeleteMapping("/delete/{id}")
   public ResponseEntity DeletPerson(@PathVariable ("id") String persongid)
   {
   	  long id =0;
   	try
   	{
   		id = Long.parseLong(persongid);
   		
   	 boolean flag= iPersonServices.DeletePerson(id);
   	 if(flag)
   	 return  ResponseEntity.noContent().build();
   	 else
   		   return ResponseEntity.notFound().build();
   			 }
   	catch (NumberFormatException e) {
           return ResponseEntity.badRequest().body("Invalid id provided");
   	}
   	catch (PersonNotFoundException e) {
   		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
   }
    
}
