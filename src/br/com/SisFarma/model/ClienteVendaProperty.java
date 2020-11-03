/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.model;



import java.time.LocalDate;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class ClienteVendaProperty {
    //private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty quant;
    private final SimpleFloatProperty valor;
    private final SimpleObjectProperty <LocalDate> data;
    private final SimpleStringProperty nomeV;
    private final SimpleStringProperty nomeC;
    private final SimpleStringProperty produto;
    
    /*private Venda venda;
    private Cliente cliente;
    */

    public ClienteVendaProperty(Venda venda, Cliente cliente, ProdutoVenda produto) {
        //this.id = new SimpleIntegerProperty(venda.getId());
        this.quant = new SimpleIntegerProperty(venda.getQuant());
        this.valor = new SimpleFloatProperty(venda.getValor());
        this.data = new SimpleObjectProperty <>(venda.getData());
        this.nomeV = new SimpleStringProperty(venda.getU().getNome());
        this.nomeC = new SimpleStringProperty(cliente.getNomeC());
        this.produto = new SimpleStringProperty(produto.getProduto().getNome());
    }

    public ClienteVendaProperty() {
        //this.id = new SimpleIntegerProperty();
        this.quant = new SimpleIntegerProperty();
        this.valor = new SimpleFloatProperty();
        this.data = new SimpleObjectProperty <>();
        this.nomeV = new SimpleStringProperty();
        this.nomeC = new SimpleStringProperty();
        this.produto = new SimpleStringProperty();
    }
    
    public String getProduto() {
        return produto.get();
    }

    public void setProduto(ProdutoVenda produto) {
        this.produto.set(produto.getProduto().getNome());
    }
    
 
    /* public int getId() {
    return id.get();
    }
    
    public void setId(Venda venda) {
    this.id.set(venda.getId());
    }*/

    public int getQuant() {
        return quant.get();
    }

    public void setQuant(Venda venda) {
        this.quant.set(venda.getQuant());
    }

    public float getValor() {
        return valor.get();
    }

    public void setValor(Venda venda) {
        this.valor.set(venda.getValor());
    }

    public LocalDate getData() {
        return data.get();
    }

    public void setData(Venda venda) {
        this.data.setValue(venda.getData());
    }

    public String getNomeV() {
        return nomeV.get();
    }

    public void setNomeV(Venda venda) {
        this.nomeV.set(venda.getU().getNome());
    }

    public String getNomeC() {
        return nomeC.get();
    }

    public void setNomeC(Cliente cliente) {
        this.nomeC.set(cliente.getNomeC());
    }
    
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(quant)
                .append(" | ").append(valor)
                .append(" | ").append(data)
                .append(" | ").append(nomeV)
                .append(" | ").append(nomeC)
                .append(" | ").append(produto);

  
        return sb.toString();
    }
    
    
}
