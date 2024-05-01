package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties("avaliacoes")
    private EmpreendedorModel empreendedor;
    private String avaliacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("avaliacoes")
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

    public EmpreendedorModel getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(EmpreendedorModel empreendedor) {
        this.empreendedor = empreendedor;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}