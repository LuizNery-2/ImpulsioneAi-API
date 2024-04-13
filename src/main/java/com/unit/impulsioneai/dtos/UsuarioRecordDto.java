package com.unit.impulsioneai.dtos;

import java.util.Date;

public record UsuarioRecordDto(String nome,
                               String email,
                               String senha,
                               String cpf,
                               Date dataNascimento) {

                                

                                
                                
                                public String getSenha() {
                                    return senha;
                                }
                            
                                

    
}
