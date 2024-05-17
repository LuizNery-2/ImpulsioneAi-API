package com.unit.impulsioneai.dtos;

import java.util.Date;

public record EmpreendedoresRecordDto( int idNicho,
        String nomeEmpreendimento,
        EnderecoRecordDto endereco,
        String biografia,
        Date dataNascimento, 
        String telefone, String site, String nomeCompleto, String cpf, String mei, String senha
        ,String email, String planoAssinatura, String facebook, String instagram, String modalidade, int numeroVisitas

    ) {

    public String getBiografia() {
       return biografia;
    }
    
}
