package com.unit.impulsioneai.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record DepoimentosRecordDto(UUID idEmpreendedor, String depoimento, int qtdEstrelas){
} 
