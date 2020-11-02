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
public class ProdutoVenda {
    private int id;
    private Venda venda;
    private Produto produto;

    public ProdutoVenda(int id, Venda venda, Produto produto) {
        this.id = id;
        this.venda = venda;
        this.produto = produto;
    }

    public ProdutoVenda(Venda venda, Produto produto) {
        this.venda = venda;
        this.produto = produto;
    }

    public ProdutoVenda() {
        this.produto = new Produto();
        this.venda = new Venda();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
