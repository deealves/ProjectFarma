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
}