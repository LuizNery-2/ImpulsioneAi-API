package com.unit.impulsioneai.dtos;

import java.util.Date;

public record EmpreendedoresRecordDto(
        String biografia,
        Date dataNascimento, 
        String telefone, String site, String nomeCompleto, String cpf, String mei, String senha, 
        String nomeEmpreendimento,String email, int planoAssinatura, String facebook, String instagram, String nicho, String modalidade
    ) {
    
}
