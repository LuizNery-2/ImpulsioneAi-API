package com.unit.impulsioneai.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EmpreendedoresRecordDto(
        String biografia,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataNascimento, 
        String telefone, String site, String nomeCompleto, String cpf, String mei, String senha, String nomeEmpreendimento
    ) {
    
}
