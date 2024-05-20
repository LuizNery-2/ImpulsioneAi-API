package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record AvaliacaoRecordDto (UUID idEmpreendedor, @NotBlank String avaliacao, UUID usuario ){
    
}
