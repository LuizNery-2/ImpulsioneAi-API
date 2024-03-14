package com.unit.impulsioneai.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="tb_nichos")
public class NichoModel implements Serializable {

    private static final long serialVersionUID = 4l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nicho;

    public int getId() {
        return id;
    }

       public String getNicho() {
        return nicho;
    }

    public void setNicho(String nichos) {
        this.nicho = nichos;
    }
}
