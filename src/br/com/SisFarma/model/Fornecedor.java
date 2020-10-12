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
    private int codigo;
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

    public Fornecedor(int codigo, String nome, String cnpj, String rua, String cidade, String estado, String cep, String telefone, String email) {
        this.codigo = codigo;
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
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
        sb.append(" | ").append(codigo)
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
