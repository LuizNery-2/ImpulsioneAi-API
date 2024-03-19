package com.unit.impulsioneai.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmpreendedoresRecordDto(@NotBlank String biografia,
                                    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")Date tempoAtuacao,
                                    @NotBlank String telefone, @NotBlank String endereco, @NotBlank String site, 
                                    @NotBlank String redeSocial, @NotNull boolean verificado) {
    
}
