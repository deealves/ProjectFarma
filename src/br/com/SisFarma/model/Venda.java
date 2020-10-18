/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.model;

import java.time.LocalDate;

/**
 *
 * @author Matrix
 */
public class Venda {
    private int id;
    private float valor;
    private LocalDate data;
    private int quant;
    private Usuario u;

    public Venda(int id, float valor, LocalDate data, int quant, Usuario u) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.quant = quant;
        this.u = u;
    }

    public Venda(float valor, LocalDate data, int quant, Usuario u) {
        this.valor = valor;
        this.data = data;
        this.quant = quant;
        this.u = u;
    }

    public Venda() {
        this.u = new Usuario();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
  
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(valor)
                .append(" | ").append(quant)
                .append(" | ").append(data)
                .append(" | ").append(u);
  
        return sb.toString();
    }
}
