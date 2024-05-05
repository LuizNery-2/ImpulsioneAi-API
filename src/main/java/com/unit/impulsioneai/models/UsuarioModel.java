package com.unit.impulsioneai.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_usuarios")
@JsonIgnoreProperties({"senha","password","accountNonExpired","accountNonLocked","credentialsNonExpired","authorities"})
public class UsuarioModel implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUsuario;
    private String nome;
    private String email;
    @JsonIgnoreProperties("senha")
    private String senha;
    private String cpf;

    @ManyToMany
    @JoinTable(
            name = "favoritos",
            joinColumns =  @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "empreendedor_id")
    )
    private List<EmpreendedorModel> empreendedoresFavoritos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("usuario")
    private List<AvaliacaoModel> avaliacoes = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<EmpreendedorModel> getEmpreendedoresFavoritos() {
        return empreendedoresFavoritos;
    }

    public void setEmpreendedoresFavoritos(List<EmpreendedorModel> empreendedoresFavoritos) {
        this.empreendedoresFavoritos = empreendedoresFavoritos;
    }

    public List<AvaliacaoModel> getAvaliacoes() {
        return avaliacoes;
    }
}
