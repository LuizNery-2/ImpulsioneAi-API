package com.unit.impulsioneai.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="tb_anuncios")
public class AnuncioModel implements Serializable {

    private static final long serialVersionUID = 6l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAnuncio;
    private String urlAnuncio;
    private String urlImagemAnuncio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataInicialAnuncio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataFinalAnuncio;
    private long cliques;

    public UUID getIdAnuncio() {
        return idAnuncio;
    }

    public String getUrlAnuncio() {
        return urlAnuncio;
    }

    public void setUrlAnuncio(String urlAnuncio) {
        this.urlAnuncio = urlAnuncio;
    }

    public Date getDataInicialAnuncio() {
        return dataInicialAnuncio;
    }

    public void setDataInicialAnuncio(Date dataInicialAnuncio) {
        this.dataInicialAnuncio = dataInicialAnuncio;
    }

    public Date getDataFinalAnuncio() {
        return dataFinalAnuncio;
    }

    public void setDataFinalAnuncio(Date dataFinalAnuncio) {
        this.dataFinalAnuncio = dataFinalAnuncio;
    }

    public long getCliques() {
        return cliques;
    }

    public void setCliques(long cliques) {
        this.cliques = cliques;
    }
}
