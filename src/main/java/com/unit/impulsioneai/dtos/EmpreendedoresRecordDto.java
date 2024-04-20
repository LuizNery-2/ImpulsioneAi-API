package com.unit.impulsioneai.dtos;

import java.util.Date;

public record EmpreendedoresRecordDto( int idNicho,
        String nomeEmpreendimento,
        String biografia,
        Date dataNascimento, 
        String telefone, String site, String nomeCompleto, String cpf, String mei, String senha
        ,String email, int planoAssinatura, String facebook, String instagram, String modalidade, int numeroVisitas

    ) {
    
}
