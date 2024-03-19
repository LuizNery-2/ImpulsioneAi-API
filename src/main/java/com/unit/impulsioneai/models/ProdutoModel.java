package com.unit.impulsioneai.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
public class ProdutoModel implements Serializable {

    private static final long serialVersionUID = 5l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;
    private String nome;
    private double preco;
    private String urlFoto;


    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private NichoModel nicho;

    public UUID getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public NichoModel getNicho() {
        return nicho;
    }

    public void setNicho(NichoModel nicho) {
        this.nicho = nicho;
    }
}
