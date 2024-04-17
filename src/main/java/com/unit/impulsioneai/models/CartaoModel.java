package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cartoes")
public class CartaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCartao;

    private String numeroCartao;
    private String nomeCartao;
    private String dataValidade;
    private String cvv;
    private String bandeira;

    public UUID getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(UUID idCartao) {
        this.idCartao = idCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    // public void setDataValidadeFormatada(String dataValidadeFormatada) throws ParseException {
    //     SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
    //     this.dataValidade = sdf.parse(dataValidadeFormatada);
    // }

    // public String getDataValidadeFormatada() {
    //     SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
    //     return sdf.format(this.dataValidade);
    // }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public void setCvvCriptografado(String encryptedCvv) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCvvCriptografado'");
    }

    

}
