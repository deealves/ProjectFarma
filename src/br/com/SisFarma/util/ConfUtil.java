/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SisFarma.util;

import java.util.ResourceBundle;

/**
 *
 * @author Washington
 */
public class ConfUtil {
    
    private static final ResourceBundle bundle = ResourceBundle.getBundle("conf");

    public static final String CONF_JDBC_URL = "conf.jdbc.url";
    public static final String CONF_JDBC_USUARIO = "conf.jdbc.usuario";
    public static final String CONF_JDBC_SENHA = "conf.jdbc.senha";
    
    public static String getConf(String key){
        return bundle.getString(key);
    }
}
