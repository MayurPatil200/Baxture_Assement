package com.example.reopsitory;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entites.Person;

@Repository		
public interface IPersonRepository  extends JpaRepository<Person, Long>{
	

}
