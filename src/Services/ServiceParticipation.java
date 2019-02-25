/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entite.Participation;
import dataSource.connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author toshiba.pc
 */
public class ServiceParticipation {
    
    Connection c= connexion.getInstance().getCnx();
    private PreparedStatement ps;
    private static ResultSet r;
    
    public void ajouterparticipation (Participation p){
        
        try {
            Statement st= c.createStatement();
            
            String req="insert into participation values ('"+p.getIdParticipation()+"','"+1+ "','"+p.getIdEvenement()+ "')";  
            
            st.executeUpdate(req);
            
            System.out.println("l'ajout d'une participation est effectué avec succés");  
                      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticipation.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public List<Participation> participeDéja(int id) {
        List<Participation> listeParticipation = new ArrayList<>();

        String requete = "select * from participation WHERE id_User = "+id; 
                
        try {
            Statement statement = c.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Participation f = new Participation();
                
               
               f.setIdParticipation(r.getInt(1));
               f.setIduser(r.getInt(2));
               f.setIdEvenement(r.getInt(3));
                listeParticipation.add(f);
                
                System.out.println("recupération de id evenement "+listeParticipation.toString());
                
            }
            return listeParticipation;
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
     }
    
      public void supprimerParticipation(Integer idEvenement,Integer idUser) {
 
       
        String req = "delete from participation where ID_USER =? and ID_EVENT=?";
        try {
            ps = c.prepareStatement(req);
            ps.setInt(1,idUser);
            ps.setInt(2,idEvenement);
            ps.executeUpdate();
            System.out.println("paricipation supprimé");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
   
   
}
    
    

