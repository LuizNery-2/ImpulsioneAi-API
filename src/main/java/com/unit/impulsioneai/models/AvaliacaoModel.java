package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="tb_avaliacoes")
public class AvaliacaoModel implements Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "empreendedor_id")
    private EmpreendedorModel empreendedor;
    private String avaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
    private int qtdEstrelas;



    public int getQtdEstrelas() {
        return qtdEstrelas;
    }
    public void setQtdEstrelas(int qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    public String getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    
}