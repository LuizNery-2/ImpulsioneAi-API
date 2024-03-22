package com.unit.impulsioneai.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UsuarioRecordDto(@NotBlank String nome,
                               @NotBlank String email,
                               @NotBlank String senha,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataNascimento) {
}
