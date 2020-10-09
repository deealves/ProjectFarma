package br.com.SisFarma.dao;



import br.com.SisFarma.model.Especificacao;
import br.com.SisFarma.model.Produto;
import br.com.SisFarma.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Washington
 */

public class ProdutoDAO {
    private Connection con;
    private String sql;
    private PreparedStatement st;

    public void inserir(Produto produto) throws Exception {

        // inserir especificação
        sql = "insert into especificacoes(fabricante, estoqueatual, detalhes) values (?,?,?)";
        
        con = ConnectionFactory.getConnection();
        
        // informa ao jdbc que o codigo gerado deverá ser retornado
        st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        st.setString(1, produto.getEspecificacao().getFabricante());
        st.setInt(2, produto.getEspecificacao().getEstoqueatual());
        st.setString(3, produto.getEspecificacao().getDetalhes());

        st.executeUpdate();
        
        ResultSet rs = st.getGeneratedKeys();
        rs = st.getGeneratedKeys();
        int codigoEspecificacao = 0;

        if (rs.next()) {
            codigoEspecificacao = rs.getInt(1);
        }
        
        // recuperar código gerado
        sql = "insert into produtos(codproduto, nome, preco, especificacao) values (?,?,?,?)";

        st = con.prepareStatement(sql);

        st.setInt(1, produto.getCodigodoproduto());
        st.setString(2, produto.getNome());
        st.setDouble(3, produto.getPreco());
        st.setInt(4, codigoEspecificacao);

        st.executeUpdate();

        con.close();
    }

    public List<Produto> listar() throws Exception {
        sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo";
        
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            
            Produto p = new Produto();
            p.setEspecificacao(new Especificacao());
            
            p.setCodigodoproduto(rs.getInt("codproduto"));
            p.setCodigo(rs.getInt("codigo"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));

            p.getEspecificacao().setCodigo(rs.getInt("especificacao"));
            p.getEspecificacao().setFabricante(rs.getString("fabricante"));
            p.getEspecificacao().setEstoqueatual(rs.getInt("estoqueatual"));
            p.getEspecificacao().setDetalhes(rs.getString("detalhes"));

            produtos.add(p);
        }
        con.close();
        return produtos;
    }
    
    public List<Produto> listar(Double preco, int ref) throws Exception {
        switch (ref) {
            case 1:
                sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo and p.preco > " + preco;
                break;
            case 0:
                sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo and p.preco = " + preco;
                break;
            case -1:
                sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo and p.preco < " + preco;
                break;
            default:
                sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo and p.preco > " + preco;
                break;
        }
        
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            
            Produto p = new Produto();
            p.setEspecificacao(new Especificacao());
            
            p.setCodigodoproduto(rs.getInt("codproduto"));
            p.setCodigo(rs.getInt("codigo"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));

            p.getEspecificacao().setCodigo(rs.getInt("especificacao"));
            p.getEspecificacao().setFabricante(rs.getString("fabricante"));
            p.getEspecificacao().setEstoqueatual(rs.getInt("estoqueatual"));
            p.getEspecificacao().setDetalhes(rs.getString("detalhes"));

            produtos.add(p);
        }
        con.close();
        return produtos;
    }

    public List<Produto> buscar(String query) throws Exception {
        
        con = ConnectionFactory.getConnection();
        sql = "select p.*, e.* from produtos p, especificacoes e where p.especificacao = e.codigo and p.nome ilike ?";
        
        st = con.prepareStatement(sql);
        st.setString(1, query + '%');
        
        ResultSet rs = st.executeQuery();
        
        List<Produto> produtos = new ArrayList<>();
        
        while(rs.next()){
            //int codigo = rs.getInt(1);
            //String nome = rs.getString("nome");
            //double preco = rs.getDouble("preco");
            //int especificacao = rs.getInt("especificacao");
            //String facricante = rs.getString("fabricante");
            //String sistema = rs.getString("sistema");
            //String detalhes = rs.getString("detalhes");
            
            Produto p = new Produto();
            p.setEspecificacao(new Especificacao());

            p.setCodigodoproduto(rs.getInt("codproduto"));
            p.setCodigo(rs.getInt("codigo"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));

            p.getEspecificacao().setCodigo(rs.getInt("especificacao"));
            p.getEspecificacao().setFabricante(rs.getString("fabricante"));
            p.getEspecificacao().setEstoqueatual(rs.getInt("estoqueatual"));
            p.getEspecificacao().setDetalhes(rs.getString("detalhes"));
            
            produtos.add(p);
        }
        con.close();
        return produtos;
    }

   public Produto buscarPorCodigo(int codigo) throws Exception {
        sql = "select p.*, e.* from produtos p, especificacoes e where p.codigo = ? and p.especificacao = e.codigo";
        
        con = ConnectionFactory.getConnection();
        st = con.prepareStatement(sql);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        
        Produto p = null;
        if (rs.next()) {
            p = new Produto();
            p.setEspecificacao(new Especificacao());

            p.setCodigodoproduto(rs.getInt("codproduto"));
            p.setCodigo(rs.getInt("codigo"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getDouble("preco"));

            p.getEspecificacao().setCodigo(rs.getInt("especificacao"));
            p.getEspecificacao().setFabricante(rs.getString("fabricante"));
            p.getEspecificacao().setEstoqueatual(rs.getInt("estoqueatual"));
            p.getEspecificacao().setDetalhes(rs.getString("detalhes"));
        }
        con.close();
        return p;
    }
    
    public void remover(Produto produto) throws Exception {
        con = ConnectionFactory.getConnection();
        
        sql = "delete from especificacoes where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, produto.getEspecificacao().getCodigo());
        st.executeUpdate();
        
        sql = "delete from produtos where codigo = ?";
        st = con.prepareStatement(sql);
        st.setInt(1, produto.getCodigo());     
        st.executeUpdate();

        con.close();
    }

    public void editar(Produto produto) throws Exception{
        sql = "update produtos set codproduto = ?, nome = ?, preco = ? where codigo = ?";
        
        con = ConnectionFactory.getConnection();

        st = con.prepareStatement(sql);
        
        st.setInt(1, produto.getCodigodoproduto());
        st.setString(2, produto.getNome());
        st.setDouble(3, produto.getPreco());
        st.setInt(4, produto.getCodigo());
                
        
        st.executeUpdate();

        sql = "update especificacoes set fabricante = ?, estoqueatual = ?, detalhes = ? where codigo = ?";

        st = con.prepareStatement(sql);

        st.setString(1, produto.getEspecificacao().getFabricante());
        st.setInt(2, produto.getEspecificacao().getEstoqueatual());
        st.setString(3, produto.getEspecificacao().getDetalhes());
        st.setInt(4, produto.getEspecificacao().getCodigo());
        st.executeUpdate();

        con.close();
    }
}