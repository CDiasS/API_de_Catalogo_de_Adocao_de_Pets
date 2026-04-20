package com.petadoption.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Map;

@Data
@Document(collection = "pets")
public class Pet {

    @Id
    private String id;

    @NotBlank(message = "O nome do pet é obrigatório.")
    private String name;

    @NotBlank(message = "A espécie do pet é obrigatória.")
    private String species;

    @Min(value = 0, message = "A idade do pet não pode ser negativa.")
    private int age;

    @NotBlank(message = "A cidade do pet é obrigatória.")
    private String city;

    private String imageUrl;

    @NotNull(message = "O status de adoção é obrigatório.")
    private AdoptionStatus adoptionStatus;

    // Para características variadas (flexibilidade do NoSQL)
    private Map<String, Object> characteristics;

    public enum AdoptionStatus {
        DISPONIVEL,
        EM_PROCESSO,
        ADOTADO
    }
}
