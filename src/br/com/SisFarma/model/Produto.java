package br.com.SisFarma.model;

/**
 *
 * @author Washington
 */

public class Produto {
    private int id;
    private int codproduto;
    private String nome;
    private float preco;
    private String fabricante;
    private int quant;
    private String descricao;
    private Cliente c;
    private Venda v;

    public Produto(int id, int codproduto, String nome, float preco, String fabricante, int quant, String descricao, Cliente c, Venda v) {
        this.id = id;
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.quant = quant;
        this.descricao = descricao;
        this.c = c;
        this.v = v;
    }

    public Produto(int codproduto, String nome, float preco, String fabricante, int quant, String descricao, Cliente c, Venda v) {
        this.codproduto = codproduto;
        this.nome = nome;
        this.preco = preco;
        this.fabricante = fabricante;
        this.quant = quant;
        this.descricao = descricao;
        this.c = c;
        this.v = v;
    }

    public Produto() {
        this.c = new Cliente();
        this.v = new Venda();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public Venda getV() {
        return v;
    }

    public void setV(Venda v) {
        this.v = v;
    }
    

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(codproduto)
                .append(" | ").append(nome)
                .append(" | ").append(preco)
                .append(" | ").append(fabricante)
                .append(" | ").append(quant)
                .append(" | ").append(descricao)
                .append(" | ").append(c)
                .append(" | ").append(v);
                
        return sb.toString();
    }
}
