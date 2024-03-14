package com.unit.impulsioneai.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanoAssinaturaRecordDto(@NotBlank String nome,@NotBlank String descricao,@NotBlank String beneficios,@NotNull Double preco) {
    
}
