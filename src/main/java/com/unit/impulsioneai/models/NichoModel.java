package com.unit.impulsioneai.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;

@Entity
@Table(name="tb_nichos")
public class NichoModel implements Serializable {

    private static final long serialVersionUID = 4l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nicho;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} , orphanRemoval = true)
    @JsonIgnoreProperties("nicho")
    private HashSet<ProdutoModel> produtos = new HashSet<>();

    public int getId() {
        return id;
    }

       public String getNicho() {
        return nicho;
    }

    public void setNicho(String nichos) {
        this.nicho = nichos;
    }

    public HashSet<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(HashSet<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
