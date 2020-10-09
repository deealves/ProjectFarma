package br.com.SisFarma.util;

/**
 *
 * @author Washington
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    ConfUtil.getConf(ConfUtil.CONF_JDBC_URL), 
                    ConfUtil.getConf(ConfUtil.CONF_JDBC_USUARIO), 
                    ConfUtil.getConf(ConfUtil.CONF_JDBC_SENHA));
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
    }
}
