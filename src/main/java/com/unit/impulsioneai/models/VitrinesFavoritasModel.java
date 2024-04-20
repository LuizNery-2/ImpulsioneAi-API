package com.unit.impulsioneai.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_vitrinesFavoritas")
public class VitrinesFavoritasModel implements Serializable{
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private UUID idEmpreededor;
    private UUID idUsuario;





    
    public UUID getIdEmpreededor() {
        return idEmpreededor;
    }
    public void setIdEmpreededor(UUID idEmpreededor) {
        this.idEmpreededor = idEmpreededor;
    }
    public UUID getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }
}
