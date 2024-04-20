package com.unit.impulsioneai.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_nichos")
public class NichoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idNicho;

    private String nicho;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} , orphanRemoval = true, mappedBy = "nicho")
    @JsonIgnoreProperties("nicho")
    private Set<ProdutoModel> produtos = new HashSet<>();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} , orphanRemoval = true, mappedBy = "nicho")
    @JsonIgnoreProperties("nicho")
    private Set<EmpreendedorModel> empreendimentos = new HashSet<>();


    public int getId() {
        return idNicho;
    }

       public String getNicho() {
        return nicho;
    }

    public void setNicho(String nichos) {
        this.nicho = nichos;
    }

    public Set<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(HashSet<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public Set<EmpreendedorModel> getEmpreendimentos() {
        return empreendimentos;
    }

    public void setEmpreendimentos(HashSet<EmpreendedorModel> empreendimentos) {
        this.empreendimentos = empreendimentos;
    }
}
