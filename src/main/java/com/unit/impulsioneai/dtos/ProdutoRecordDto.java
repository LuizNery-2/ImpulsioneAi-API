package com.unit.impulsioneai.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRecordDto(@NotBlank String nome, @NotNull double preco, String urlFoto) {
}
