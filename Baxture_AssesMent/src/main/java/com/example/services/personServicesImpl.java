package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entites.Person;

import com.example.excepations.PersonNotFoundException;
import com.example.reopsitory.IPersonRepository;
@Service
public class personServicesImpl implements IPersonServices {

	@Autowired
	private IPersonRepository iPersonRepository;
	
	@Override
	public List<Person> getallpersons() {
		   List<Person> personlist = iPersonRepository.findAll();
		   if(personlist==null)
		return null;
		   else
			   return personlist;
	}

	@Override
	public Optional<Person> getpersonbyid(long id) {
		// TODO Auto-generated method stub
		Optional<Person> optionalPerson = iPersonRepository.findById(id);

        if (optionalPerson.isPresent()) {
        	 return optionalPerson;
        } else {
            throw new PersonNotFoundException("Person not found for ID: " + id);
        }
	}
	

	@Override
	public Person AddPerson(Person person) {
		   iPersonRepository.save(person);
		return person;
	}

	@Override
	public Person Updatedetail(long id,Person person) {
		// TODO Auto-generated method stub
		Person existPerson = iPersonRepository.findById(id).orElse(null);
		if(existPerson == null)
		{
		  throw new PersonNotFoundException("Person Not Found for this id");
		}
		  existPerson.setAge(person.getAge());
		  existPerson.setHobbies(person.getHobbies());
		  existPerson.setUsername(person.getUsername());
		   iPersonRepository.save(existPerson);
		return existPerson;
	}

	@Override
	public boolean DeletePerson(long id) {
		
		  
		
		Person existPerson = iPersonRepository.findById(id).orElse(null);
		if(existPerson == null)
		{
		  throw new PersonNotFoundException("Person Not Found for this id");
		}
		else
		{
			iPersonRepository.deleteById(id);
			return true;
		}
		
	}

	

	



	

}
