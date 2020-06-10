/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ach
 */
public class ConnectDB {
  String url = "jdbc:mysql://localhost:3306/pi-dev";
    String login = "root";
    String pwd = "";
    public static ConnectDB db;
    public Connection con;

    private ConnectDB() {
        try {
            url = "jdbc:mysql://localhost:3306/pi-dev";
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion Établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static ConnectDB getInstance() {
        if (db == null) {
            db = new ConnectDB();
        }
        return db;
    }
}