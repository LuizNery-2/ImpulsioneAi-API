package com.unit.impulsioneai.models;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name = "tb_endereco")
public class EnderecoModel implements Serializable{
    @Serial
    private static final long  serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String idEndereco;

    @OneToOne
    @JoinColumn(name = "empreendedor_id")
    @JsonIgnoreProperties("endereco")
    private EmpreendedorModel empreendedor;
    private String uf;
    private String  cidade;
    private String bairro;
    private String logradouro;
    private String numero;


    public EmpreendedorModel getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(EmpreendedorModel empreendedorModel) {
        this.empreendedor = empreendedorModel;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
