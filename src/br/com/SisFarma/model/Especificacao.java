package br.com.SisFarma.model;

/**
 *
 * @author Washington
 */

public class Especificacao {
    private int codigo;
    private String fabricante;
    private int estoqueatual;
    private String detalhes;

    public Especificacao(int codigo, String nome, String fabricante, int estoqueatual, String detalhes) {
        this.codigo = codigo;
        this.fabricante = fabricante;
        this.estoqueatual = estoqueatual;
        this.detalhes = detalhes;
    }

    public Especificacao() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getEstoqueatual() {
        return estoqueatual;
    }

    public void setEstoqueatual(int estoqueatual) {
        this.estoqueatual = estoqueatual;
    }
    
    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" | ");
        sb.append(codigo)
                .append(" | ").append(fabricante)
                .append(" | ").append(estoqueatual)
                .append(" | ").append(detalhes)
                .append(" | ");
        return sb.toString();
    }
}
