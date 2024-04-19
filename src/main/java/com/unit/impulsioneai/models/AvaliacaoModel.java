package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_avaliacoes")
public class AvaliacaoModel implements Serializable{
    private static final long serialVersionUID = 1l;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private UUID idEmpreededor;
    private String avaliacao;
    private String nomeUsuario;
    private int qdtEstrelas;



    public int getQdtEstrelas() {
        return qdtEstrelas;
    }
    public void setQdtEstrelas(int qdtEstrelas) {
        this.qdtEstrelas = qdtEstrelas;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public String getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }
    public UUID getIdEmpreededor() {
        return idEmpreededor;
    }
    public void setIdEmpreededor(UUID idEmpreededor) {
        this.idEmpreededor = idEmpreededor;
    }
    
}