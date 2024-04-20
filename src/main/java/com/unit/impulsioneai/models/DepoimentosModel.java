package com.unit.impulsioneai.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_depoimentos")
public class DepoimentosModel implements Serializable{
    @Serial
    private static final long serialVersionUID = 9L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private UUID idEmpreededor;
    private String depoimento;
    private String nomeUsuario;



    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public String getDepoimento() {
        return depoimento;
    }
    public void setDepoimento(String depoimento) {
        this.depoimento = depoimento;
    }
    public UUID getIdEmpreededor() {
        return idEmpreededor;
    }
    public void setIdEmpreededor(UUID idEmpreededor) {
        this.idEmpreededor = idEmpreededor;
    }
    
}
