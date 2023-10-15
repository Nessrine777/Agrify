/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Ahmed Raies
 */
public class MyConnection {
      public String url="jdbc:mysql://localhost:3306/dbagrifyps";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance;



    private MyConnection () {
        try {
          cnx = DriverManager.getConnection(url, login, pwd);
          System.out.println("Connexion établie avec succès !");
        } catch (SQLException e) {
          //  throw new RuntimeException(e);
            System.err.println(e.getMessage());
        }
    }

    public Connection getCnx(){
        return cnx;
    }
    
     public static MyConnection getInstance() {
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
}
