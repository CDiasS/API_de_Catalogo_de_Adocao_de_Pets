package com.petadoption.api.service;

import com.petadoption.api.model.Pet;
import com.petadoption.api.model.Pet.AdoptionStatus;
import com.petadoption.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(String id) {
        return petRepository.findById(id);
    }

    public Pet createPet(Pet pet) {
        // Lógica de validação adicional pode ser adicionada aqui, se necessário
        return petRepository.save(pet);
    }

    public Pet updatePet(String id, Pet petDetails) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com o ID: " + id));

        pet.setName(petDetails.getName());
        pet.setSpecies(petDetails.getSpecies());
        pet.setAge(petDetails.getAge());
        pet.setCity(petDetails.getCity());
        pet.setImageUrl(petDetails.getImageUrl());
        pet.setAdoptionStatus(petDetails.getAdoptionStatus());
        pet.setCharacteristics(petDetails.getCharacteristics());

        return petRepository.save(pet);
    }

    public void deletePet(String id) {
        if (!petRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com o ID: " + id);
        }
        petRepository.deleteById(id);
    }

    public List<Pet> searchPets(String species, Integer age, String city) {
        if (species != null && age != null && city != null) {
            return petRepository.findBySpeciesAndAgeAndCity(species, age, city);
        } else if (species != null && age != null) {
            return petRepository.findBySpeciesAndAge(species, age);
        } else if (species != null && city != null) {
            return petRepository.findBySpeciesAndCity(species, city);
        } else if (age != null && city != null) {
            return petRepository.findByAgeAndCity(age, city);
        } else if (species != null) {
            return petRepository.findBySpecies(species);
        } else if (age != null) {
            return petRepository.findByAge(age);
        } else if (city != null) {
            return petRepository.findByCity(city);
        } else {
            return petRepository.findAll();
        }
    }

    public Pet updateAdoptionStatus(String id, AdoptionStatus newStatus) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com o ID: " + id));

        // Exemplo de lógica de negócio: um pet só pode ser adotado se estiver disponível ou em processo
        if (pet.getAdoptionStatus() == AdoptionStatus.ADOTADO && newStatus != AdoptionStatus.ADOTADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível alterar o status de um pet já adotado para outro que não seja 'ADOTADO'.");
        }
        
        pet.setAdoptionStatus(newStatus);
        return petRepository.save(pet);
    }
}
