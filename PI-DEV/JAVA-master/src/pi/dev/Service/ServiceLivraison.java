/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pi.dev.Entity.Livraison;
import pi.dev.IService.IService;
import pi.dev.Utility.ConnectDB;

/**
 *
 * @author Ach
 */
public class ServiceLivraison implements IService<Livraison> {

    private Connection con;
    private Statement ste;

    public ServiceLivraison() {
        con = ConnectDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Livraison t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pi-dev`.`livraison` (`id`, `nameClient`, `street`, `city`, `zipCode`, `phoneNum`, `email`) VALUES (NULL, '"
                + t.getNameClient() + "', '" + t.getStreet() + "', '" + t.getCity() + "', '" + t.getZipCode() + "', '" + t.getPhoneNum() + "', '" + t.getEmail() + "');";
        ste.executeUpdate(requeteInsert);
    }

    public void ajouter1(Livraison l) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `pi-dev`.`livraison` ( `nameClient`, `street`, `city`, `zipCode`, `phoneNum`, `email`) VALUES ( ?, ?, ?, ?, ?, ?);");
        pre.setString(1, l.getNameClient());
        pre.setString(2, l.getStreet());
        pre.setString(3, l.getCity());
        pre.setInt(4, l.getZipCode());
        pre.setInt(5, l.getPhoneNum());
        pre.setString(6, l.getEmail());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Livraison t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Livraison t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livraison> readAll() throws SQLException {
        List<Livraison> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from livraison");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nameClient = rs.getString("nameClient");
            String street = rs.getString("street");
            String city = rs.getString("city");
            int zipCode = rs.getInt("zipCode");
            int phoneNum = rs.getInt("phoneNum");
            String email = rs.getString("email");
            Livraison l = new Livraison(id, nameClient, street, city, zipCode, phoneNum, email);
            arr.add(l);
        }
        return arr;
    }
}
