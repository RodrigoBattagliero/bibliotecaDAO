/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecadao.conexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public class Conexion {
    private static Conexion instance;
    private Connection cnn;
    
    private Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cnn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "rodrigo");
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
    }
    
    public synchronized static Conexion estado(){
        if(instance == null) instance = new Conexion();
        return instance;
    }
    
    public Connection getCnn(){
        return cnn;
    }
    
    public void closeConecxion(){
        instance = null;
    }
    
}
