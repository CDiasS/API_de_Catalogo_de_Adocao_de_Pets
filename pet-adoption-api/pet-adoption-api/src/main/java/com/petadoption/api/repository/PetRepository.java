package com.petadoption.api.repository;

import com.petadoption.api.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {
    List<Pet> findBySpecies(String species);
    List<Pet> findByAge(int age);
    List<Pet> findByCity(String city);
    List<Pet> findBySpeciesAndAge(String species, int age);
    List<Pet> findBySpeciesAndCity(String species, String city);
    List<Pet> findByAgeAndCity(int age, String city);
    List<Pet> findBySpeciesAndAgeAndCity(String species, int age, String city);
}
