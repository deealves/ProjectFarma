package br.com.SisFarma.model;

/**
 *
 * @author Washington
 */

public class Produto {
    private int codigo;
    private int codigodoproduto;
    private String nome;
    private double preco;
    private Especificacao especificacao;

    public Produto(int codigo, int codigodoproduto, String nome, double preco, Especificacao especificacao) {
        this.codigo = codigo;
        this.codigodoproduto = codigodoproduto;
        this.nome = nome;
        this.preco = preco;
        this.especificacao = especificacao;
    }

    public Produto() {
        especificacao = new Especificacao();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigodoproduto() {
        return codigodoproduto;
    }

    public void setCodigodoproduto(int codigodoproduto) {
        this.codigodoproduto = codigodoproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Especificacao getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(Especificacao especificacao) {
        this.especificacao = especificacao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" | ").append(codigo)
                .append(" | ").append(nome)
                .append(" | ").append(preco)
                .append(" | ").append(especificacao);
        return sb.toString();
    }
}
