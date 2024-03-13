package com.unit.impulsioneai.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "tb_empreendedor")
public class EmpreendedorModel implements Serializable {

    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmpreededor;

    @Column(columnDefinition = "TEXT")
    private String biografia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date tempoAtuacao;


    private String telefone;

    private String endereco;
    private String site;
    private String redeSocial;

    private boolean verificado;

    public UUID getIdEmpreededor() {
        return idEmpreededor;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Date getTempoAtuacao() {
        return tempoAtuacao;
    }

    public void setTempoAtuacao(Date tempoAtuacao) {
        this.tempoAtuacao = tempoAtuacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRedeSocial() {
        return redeSocial;
    }

    public void setRedeSocial(String redeSocial) {
        this.redeSocial = redeSocial;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}
