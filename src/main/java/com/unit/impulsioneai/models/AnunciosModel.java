package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tb_anuncios")
public class AnunciosModel  implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAnuncio;

    private Date dataInicio;
    private Date dataTermino;
    private int clique;
    
    private String planoAssinatura;





    public String getPlanoAssinatura() {
        return planoAssinatura;
    }
    public void setPlanoAssinatura(String planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataTermino() {
        return dataTermino;
    }
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    public int getClique() {
        return clique;
    }
    public void setClique(int clique) {
        this.clique = clique;
    }
}
