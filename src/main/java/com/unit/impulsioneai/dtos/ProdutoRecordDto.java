package com.unit.impulsioneai.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProdutoRecordDto(@NotBlank String nome,
                               @NotNull double preco,
                               String urlFoto,
                               UUID idEmpreendedor) {
}
