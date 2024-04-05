package com.unit.impulsioneai.dtos;

import java.util.UUID;

public record AuthenticatedResponseRecordDto(String token , UUID idUsuario) {
}
