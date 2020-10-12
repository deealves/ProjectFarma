/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.model;

/**
 *
 * @author Matrix
 */
public class Venda {
    private long id;
    private int codproduto;
    private String nome;
    private float preco;
    private int quant;

    public Venda(long id, int codproduto, String nome, float preco, int quant) {
        this.id = id;
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.quant = quant;
    }

    public Venda(int codproduto, String nome, float preco, int quant) {
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.quant = quant;
    }

    public Venda() {
        
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodproduto() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto = codproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
    public void mostraVenda(){
        System.out.println(" / "+ getId()
                          +" / "+ getCodproduto()
                          +" / "+ getNome()
                          +" / "+ getPreco()                  
                          +" / "+ getQuant());
    }
}
