package com.unit.impulsioneai.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record AnuncioRecordDto(@NotBlank String urlAnuncio,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataInicialAnuncio,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataFinalAnuncio,
                               String urlImagemAnuncio
                               ) {
}
