package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record PlanoAssinaturaRecordDto(@NotBlank UUID idPlanoAssinatura,@NotBlank String nome,@NotBlank String descricao,@NotBlank String beneficios,@NotBlank Double preco) {
    
}
