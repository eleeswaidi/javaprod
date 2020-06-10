/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import pi.dev.Utility.BCrypt;
import pi.dev.Utility.ConnectDB;

/**
 *
 * @author ramij
 */
public class ServiceUtilisateur {

    private Connection con;
    private Statement ste;

    public ServiceUtilisateur() {
        con = ConnectDB.getInstance().getConnection();

    }

    public SessionUser Connecter(String password, String username) {
        try {
            ste = con.createStatement();

            ResultSet rs = ste.executeQuery("select e.* from fos_user e where username='" + username + "'");
            while (rs.next()) {
                String passwordd = rs.getString("password");
                int idfos = rs.getInt(1);

                SessionUser su;

                if (BCrypt.checkpw(password, passwordd)) {
                    String roles = rs.getString("roles");
                    String email = rs.getString("email");
                    Timestamp lastLog = rs.getTimestamp("last_login");
                    if (roles.contains("ADMIN")) {
                        su = new SessionUser(idfos, "admin", username, email, lastLog);
                        return su;
                    } else {
                        su = new SessionUser(idfos, "client", username, email, lastLog);
                        return su;

                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("probleme de connexion");
        }
        return null;
    }

    public boolean verifUsername(String username) {
        try {
            ste = con.createStatement();

            ResultSet rs = ste.executeQuery("select e.* from fos_user e where username='" + username + "'");

            while (rs.next()) {
                return false;

            }

        } catch (SQLException sq) {
            return false;
        }
        return true;
    }

    

    public class SessionUser {

        private int idFos;
        private String typeFos;
        private String username;
        private String email;
        private Timestamp lastLogin;

        public SessionUser() {

        }

        public SessionUser(int idFos, String typeFos, String username, String email, Timestamp lastlogin) {
            this.idFos = idFos;
            this.typeFos = typeFos;
            this.username = username;
            this.lastLogin = lastlogin;
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Timestamp getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Timestamp lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getIdFos() {
            return idFos;
        }

        public void setIdFos(int idFos) {
            this.idFos = idFos;
        }

        public String getTypeFos() {
            return typeFos;
        }

        public void setTypeFos(String typeFos) {
            this.typeFos = typeFos;
        }

    }

}
