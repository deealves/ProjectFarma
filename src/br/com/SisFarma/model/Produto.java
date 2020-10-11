package br.com.SisFarma.model;

/**
 *
 * @author Washington
 */

public class Produto {
    private long id;
    private int codproduto;
    private String nome;
    private float preco;
    private String fabricante;
    private int quant;

    public Produto(long id, int codproduto, String nome, float preco,String fabricante, int quant) {
        this.id = id;
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.quant = quant;
    }

    public Produto(int codproduto, String nome, float preco,String fabricante, int quant) {
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.quant = quant;
    }

    public Produto() {
      
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
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
    
   
    public void mostraProduto(){
        System.out.println(" / "+ getId()
                          +" / "+ getCodproduto()
                          +" / "+ getNome()
                          +" / "+ getPreco()
                          +" / "+ getFabricante()
                          +" / "+ getQuant());
    }
}
