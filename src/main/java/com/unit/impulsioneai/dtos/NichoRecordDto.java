package com.unit.impulsioneai.dtos;

import jakarta.validation.constraints.NotBlank;

public record NichoRecordDto(@NotBlank String nicho) {
}
