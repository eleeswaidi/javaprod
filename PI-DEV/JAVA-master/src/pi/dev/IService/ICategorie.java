/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author elee
 */
public interface ICategorie <T> { 
   

    void ajouter (T t) throws SQLException;

    void supprimer( int id ) throws SQLException;

    void update (String nomm ) throws SQLException;

    List<T> readAll() throws SQLException;
    
}
