package com.unit.impulsioneai.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "tb_endereco")
public class EnderecoModel implements Serializable{
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int  idEndereco;

    private String cpfEmpreendedor;
    private String uf;
    private String  cidade;
    private String bairro;
    private String logradouro;
    private String numero;


    
    public String getCpfEmpreendedor() {
        return cpfEmpreendedor;
    }
    public void setCpfEmpreendedor(String cpfEmpreendedor) {
        this.cpfEmpreendedor = cpfEmpreendedor;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
