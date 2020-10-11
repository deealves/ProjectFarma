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
       sql = "INSERT INTO produto (codproduto,nome,preco,fabricante,quant) VALUES (?,?,?,?,?)";
       
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,p.getCodproduto());
            stmt.setString(2,p.getNome());
            stmt.setFloat(3,p.getPreco());
            stmt.setString(4,p.getFabricante());
            stmt.setInt(5,p.getQuant());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } 
        
    
    
     public boolean update(Produto p) throws SQLException{
            sql = "UPDATE produto SET codproduto = ?, nome = ?, preco = ?, fabricante = ?, quant = ? WHERE id=?";
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,p.getCodproduto());
            stmt.setString(2,p.getNome());
            stmt.setFloat(3,p.getPreco());
            stmt.setString(4,p.getFabricante());
            stmt.setInt(5,p.getQuant());
            stmt.setLong(6,p.getId());
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        
        
    }
     
     public boolean delete(Produto p){
        sql = "DELETE FROM produto WHERE id=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,p.getId());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public List<Produto> listar() throws SQLException{
         sql = "select p.id, p.codproduto, p.nome, p.preco, p.fabricante, p.quant from produto p ";
         con = ConnectionFactory.getConnection();
         stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         List<Produto> produtos = new ArrayList<>();
         while (rs.next()){
             
             Produto p = new Produto();
             
             p.setId(rs.getLong("id"));
             p.setCodproduto(rs.getInt("codproduto"));
             p.setNome(rs.getString("nome"));
             p.setPreco(rs.getFloat("preco"));
             p.setFabricante(rs.getString("fabricante"));
             p.setQuant(rs.getInt("quant"));
             
             produtos.add(p);
         }
         
         con.close();
         return produtos;
        
     }
}