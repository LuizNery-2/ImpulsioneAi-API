package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DepoimentosRecordDto(UUID idEmpreededor, @NotBlank String depoimento, String nomeUsuario ){
} 
