package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record AvaliacaoRecordDto (UUID idEmpreededor, @NotBlank String avaliacao, String nomeUsuario, int qdtEstrelas ){
    
}
