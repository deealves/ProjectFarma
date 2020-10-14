/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.model;

/**
 *
 * @author diego
 */
public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    //private Endereco end;
    //private Contato cont;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String email;

    public Fornecedor(int id, String nome, String cnpj, String rua, String cidade, String estado, String cep, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }

    public Fornecedor(String nome, String cnpj, String rua, String cidade, String estado, String cep, String telefone, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }
    
    public Fornecedor() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(id)
                .append(" | ").append(nome)
                .append(" | ").append(cnpj)
                .append(" | ").append(rua)
                .append(" | ").append(cidade)
                .append(" | ").append(estado)
                .append(" | ").append(cep)
                .append(" | ").append(telefone)
                .append(" | ").append(email);
    
        return sb.toString();
    }
    
    
    
}
