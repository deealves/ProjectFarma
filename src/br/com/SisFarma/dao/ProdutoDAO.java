package br.com.SisFarma.dao;



import br.com.SisFarma.model.Produto;
import br.com.SisFarma.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Washington
 */

public class ProdutoDAO {
    private Connection con;
    private String sql;
    private PreparedStatement stmt;
    
    public ProdutoDAO() {
        this.con = ConnectionFactory.getConnection();
    }
 
   public boolean insert(Produto p) throws SQLException {
        sql = "INSERT INTO produto (codproduto, nome, preco, fabricante, descricao, quant) VALUES (?,?,?,?,?,?)";

            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,p.getCodproduto());
            stmt.setString(2,p.getNome());
            stmt.setFloat(3,p.getPreco());
            stmt.setString(4,p.getFabricante());
            stmt.setString(5, p.getDescricao());
            stmt.setInt(6,p.getQuant());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } 
   
    public boolean update(Produto p) throws SQLException{
        sql = "UPDATE produto SET codproduto = ?, nome = ?, preco = ?, fabricante = ?, descricao = ?, quant = ? WHERE id=?";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,p.getCodproduto());
        stmt.setString(2,p.getNome());
        stmt.setFloat(3,p.getPreco());
        stmt.setString(4,p.getFabricante());
        stmt.setString(5, p.getDescricao());
        stmt.setInt(6,p.getQuant());
        stmt.setInt(7,p.getId());
        stmt.executeUpdate();
        stmt.close();
        con.close();
        return true;
    
    }
     
    public boolean delete(Produto p) throws SQLException{
        sql = "DELETE FROM produto WHERE id=?";
        con = ConnectionFactory.getConnection();
        stmt = con.prepareStatement(sql);
        stmt.setInt(1,p.getId());
        stmt.execute();
        stmt.close();
        con.close();
        return true;
     
    }
     
     public List<Produto> listar() throws SQLException{
         sql = "select p.id, p.codproduto, p.nome, p.preco, p.fabricante, p.descricao, p.quant from produto p ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Produto> produtos = new ArrayList<>();
         while (rs.next()){
             
             Produto p = new Produto();
             
             p.setId(rs.getInt("id"));
             p.setCodproduto(rs.getInt("codproduto"));
             p.setNome(rs.getString("nome"));
             p.setPreco(rs.getFloat("preco"));
             p.setFabricante(rs.getString("fabricante"));
             p.setDescricao(rs.getString("descricao"));
             p.setQuant(rs.getInt("quant"));
             
             produtos.add(p);
         }
         
         con.close();
         return produtos;
        
     }
     
     public List<Produto> buscar(String query) throws SQLException{
        List<Produto> lista = new ArrayList<>();

        
        sql = "select p.* from produto p  where p.nome ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, query + '%');
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt(1);
            int Codproduto = rs.getInt("codproduto");
            String nome = rs.getString("nome");
            float preco = rs.getFloat("preco");
            String fabricante = rs.getString("fabricante");
            String descricao = rs.getString("descricao");
            int quant = rs.getInt("quant");
            
            
            
            
            Produto p = new Produto();
            
            p.setId(id);
            p.setCodproduto(Codproduto);
            p.setNome(nome);
            p.setPreco(preco);
            p.setFabricante(fabricante);
            p.setDescricao(descricao);
            p.setQuant(quant);
            
            
            
            lista.add(p);
        }
        con.close();
        return lista;
    }
}