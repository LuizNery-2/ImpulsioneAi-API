package com.unit.impulsioneai.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRecordDTO(@NotBlank String email,
                             @NotBlank String senha) {
}
