/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**i
 *
 * @author toshiba.pc
 */
public class connexion {

    public static PreparedStatement PreparedStatement(String req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private  Connection cnx;

    public Connection getCnx() {
        return cnx;
    }

    public static connexion getCc() {
        return cc;
    }
     
     static connexion cc;
    
    
       private connexion(){
        
        	String  utilisateur= "root";
			String motPasse="";
			try
				{
				
				cnx= (Connection) DriverManager.getConnection(  
			          "jdbc:mysql://localhost:3306/techevents",utilisateur, motPasse);
                                System.out.println("connexion reussite");
                                }
			catch (SQLException e)
				{System.out.println("erreur pendant la connexion");}
        
    }
       
       public static connexion getInstance(){
           
           if (cc==null){
               cc =  new connexion();
           }
           
           return cc;
       }
       
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
