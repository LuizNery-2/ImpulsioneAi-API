package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DepoimentosRecordDto(UUID idEmpreendedor, @NotBlank String depoimento, int qtdEstrelas){
} 
