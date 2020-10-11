/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class Cliente {
    private int codigo;
    private String nome;
    private String cpf;
    //private Endereco end;
    //private Contato cont;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String telefone;
    private String email;

    public Cliente() {
    }

    
    public Cliente(int codigo, String nome, String cpf, String rua, String cidade, String estado, String cep, String telefone, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
    }
 
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        sb.append(" | ").append(codigo)
                .append(" | ").append(nome)
                .append(" | ").append(cpf)
                .append(" | ").append(rua)
                .append(" | ").append(cidade)
                .append(" | ").append(estado)
                .append(" | ").append(cep)
                .append(" | ").append(telefone)
                .append(" | ").append(email);
    
        return sb.toString();
    }
}
