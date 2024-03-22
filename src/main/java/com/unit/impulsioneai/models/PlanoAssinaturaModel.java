package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name =  "tb_plano_assinatura")
public class PlanoAssinaturaModel implements Serializable{
    private static final long serialVersionUID = 2l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPlanoAssinatura;

    private String nome;
    private String descricao;
    private Double preco;
    private String beneficios;



    //Getters and Setters
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public String getBeneficios() {
        return beneficios;
    }
    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }






}
