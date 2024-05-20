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
@Table(name = "tb_empreendedor")
@JsonIgnoreProperties({"senha","password","accountNonExpired","accountNonLocked","credentialsNonExpired","authorities"})
public class EmpreendedorModel implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmpreededor;

    @Column(columnDefinition = "TEXT")
    private String biografia;


    @OneToMany(mappedBy = "empreendedor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empreendedor")
    private List<DepoimentosModel> depoimento = new ArrayList<>();


    @OneToOne(mappedBy = "empreendedor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empreendedor")
    private EnderecoModel endereco;

    private int numeroFavoritos;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    private String telefone;
    private String site;
    private String nomeCompleto;
    private String cpf;
    private String mei;
    private boolean verificado = false;
    private String senha;
    private String nomeEmpreendimento;
    private String email;
    private String planoAssinatura;
    private String facebook;
    private String instagram;
    private  String nomeExibicao;

    @OneToOne(mappedBy = "empreendedor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empreendedor")
    private CartaoModel cartao;

    @ManyToOne
    @JsonIgnoreProperties({"empreendimentos","produtos"})
    private NichoModel nicho;

    @OneToMany(mappedBy = "empreendedor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empreendedor")
    private Set<ProdutoModel> produtos = new HashSet<>();

    @OneToMany(mappedBy = "empreendedor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("empreendedor")
    private List<AvaliacaoModel> avaliacoes = new ArrayList<>();
    private String modalidade;
    private int numeroVisitas;

    public int getNumeroVisitas() {
        return numeroVisitas;
    }

    public void setNumeroVisitas(int numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public NichoModel getNicho() {
        return nicho;
    }

    public void setNicho(NichoModel nicho) {
        this.nicho = nicho;
    }

    public Set<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(HashSet<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlanoAssinatura() {
        return planoAssinatura;
    }

    public void setPlanoAssinatura(String planoAssinatura) {
        this.planoAssinatura = planoAssinatura;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMei() {
        return mei;
    }

    public void setMei(String mei) {
        this.mei = mei;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeEmpreendimento() {
        return nomeEmpreendimento;
    }

    public void setNomeEmpreendimento(String nomeEmpreendimento) {
        this.nomeEmpreendimento = nomeEmpreendimento;
    }

    public UUID getIdEmpreededor() {
        return idEmpreededor;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_EMPREENDEDOR"));
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
        return verificado;
    }

    public List<DepoimentosModel> getDepoimento() {
        return depoimento;
    }

    public void setDepoimento(List<DepoimentosModel> depoimento) {
        this.depoimento = depoimento;
    }

    public void setProdutos(Set<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    public int getNumeroFavoritos() {
        return numeroFavoritos;
    }

    public void setNumeroFavoritos(int numeroFavoritos) {
        this.numeroFavoritos = numeroFavoritos;
    }

    public List<AvaliacaoModel> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoModel> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    // private Date tempoAtuacao;

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public CartaoModel getCartao() {
        return cartao;
    }

    public void setCartao(CartaoModel cartao) {
        this.cartao = cartao;
    }

   
}
