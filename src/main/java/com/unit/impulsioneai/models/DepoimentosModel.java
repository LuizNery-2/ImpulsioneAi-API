package com.unit.impulsioneai.models;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table(name="tb_depoimentos")
public class DepoimentosModel implements Serializable{
    @Serial
    private static final long serialVersionUID = 9L;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JsonIgnoreProperties("depoimento")
    private EmpreendedorModel empreendedor;

    @Column(columnDefinition = "TEXT")
    private String depoimento;

    private int qtdEstrelas;

    public int getQtdEstrelas() {
        return qtdEstrelas;
    }

    public void setQtdEstrelas(int qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    public String getId() {
        return id;
    }

    public String getDepoimento() {
        return depoimento;
    }
    public void setDepoimento(String depoimento) {
        this.depoimento = depoimento;
    }

    public EmpreendedorModel getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(EmpreendedorModel empreendedor) {
        this.empreendedor = empreendedor;
    }


}
