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
public class ClienteVenda {
    private int id;
    private Cliente cliente;
    private Venda venda;

    public ClienteVenda(int id, Cliente cliente, Venda venda) {
        this.id = id;
        this.cliente = cliente;
        this.venda = venda;
    }

    public ClienteVenda(Cliente cliente, Venda venda) {
        this.cliente = cliente;
        this.venda = venda;
    }

    public ClienteVenda() {
        this.cliente = new Cliente();
        this.venda = new Venda();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(id)
                .append(" | ").append(cliente)
                .append(" | ").append(venda);
  
        return sb.toString();
    }
 
}
