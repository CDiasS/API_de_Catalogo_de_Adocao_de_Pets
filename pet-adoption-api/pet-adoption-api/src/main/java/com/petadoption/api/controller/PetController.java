package com.petadoption.api.controller;

import com.petadoption.api.model.Pet;
import com.petadoption.api.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Lista todos os pets disponíveis para adoção")
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @Operation(summary = "Busca um pet pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet encontrado",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@Parameter(description = "ID do pet a ser buscado") @PathVariable String id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado com o ID: " + id));
    }

    @Operation(summary = "Cadastra um novo pet para adoção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet cadastrado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados do pet inválidos",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza os dados de um pet existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados do pet inválidos",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@Parameter(description = "ID do pet a ser atualizado") @PathVariable String id, @Valid @RequestBody Pet petDetails) {
        Pet updatedPet = petService.updatePet(id, petDetails);
        return ResponseEntity.ok(updatedPet);
    }

    @Operation(summary = "Exclui um pet do catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet excluído com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content) })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePet(@Parameter(description = "ID do pet a ser excluído") @PathVariable String id) {
        petService.deletePet(id);
    }

    @Operation(summary = "Busca pets com filtros avançados")
    @GetMapping("/search")
    public List<Pet> searchPets(
            @Parameter(description = "Espécie do pet (ex: cachorro, gato)") @RequestParam(required = false) String species,
            @Parameter(description = "Idade do pet") @RequestParam(required = false) Integer age,
            @Parameter(description = "Cidade onde o pet está localizado") @RequestParam(required = false) String city) {
        return petService.searchPets(species, age, city);
    }

    @Operation(summary = "Atualiza o status de adoção de um pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status de adoção atualizado",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pet.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Status de adoção inválido ou transição não permitida",
                    content = @Content) })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Pet> updateAdoptionStatus(
            @Parameter(description = "ID do pet") @PathVariable String id,
            @Parameter(description = "Novo status de adoção (DISPONIVEL, EM_PROCESSO, ADOTADO)") @RequestParam Pet.AdoptionStatus newStatus) {
        Pet updatedPet = petService.updateAdoptionStatus(id, newStatus);
        return ResponseEntity.ok(updatedPet);
    }
}
