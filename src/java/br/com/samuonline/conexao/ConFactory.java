/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.samuonline.conexao;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Felipe
 */
public class ConFactory {

    /**
     * Inica conexão com o banco de dados utilizando o pool de conexões.
     *
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {

        try {
            //POOL TOMCAT
            Context contextoInicial = new InitialContext();
            Context contextoRaiz = (Context) contextoInicial.lookup("java:/comp/env");
            DataSource ds = (DataSource) contextoRaiz.lookup("jdbc/SamuOnlinePool");
            return ds.getConnection();
        } catch (Exception ex) {
            throw ex;
        }

    }
}
