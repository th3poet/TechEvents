/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entite.Evenement;
import dataSource.connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author toshiba.pc
 */
public class ServiceEvenement {
        Connection c= connexion.getInstance().getCnx();

     public void remove(Evenement t) {
    String req = "delete from evenement where ID_EVENT =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = c.prepareStatement(req);
            preparedStatement.setInt(1, t.getID_EVENT());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
     }
     
      public void supprimerEvenement(Integer idEvenement) {
       
        String req = "delete from evenement where ID_EVENT =?";
        try {
         PreparedStatement   ps = c.prepareStatement(req);
            ps.setInt(1,idEvenement);
            ps.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
     public  ObservableList<Evenement> getallEvenement(){
          ObservableList<Evenement> Evenement = FXCollections.observableArrayList();
          
          try {
             Statement st= c.createStatement();
            String req="select * from  evenement";
            ResultSet result =st.executeQuery(req);
            result.beforeFirst();
            
              while (result.next()) {
                  Evenement u = new Evenement();
                  u.setID_EVENT(result.getInt(1));
                  u.setID_USER(result.getInt(2));
                  u.setID_ORGANISATEUR(result.getInt(3));
                  u.setNOM_EVENT(result.getString(4));
                  u.setDESC_EVENT(result.getString(5));
                   u.setDateEvenement(result.getDate(6));
                   u.setEtat(result.getString(7));
                   u.setDuree(result.getString(8));
                   u.setImage(result.getString(9));
                   u.setType(result.getString(10));
                   u.setNbrPlaces(result.getInt(11));
                   u.setPrix(result.getInt(12));
                   
                   
                   
                  
                  Evenement.add(u);
                  System.out.println(u);
                  
              }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          return Evenement;
      }
        public void ajouterevenement(Evenement p){
        
        
try {
            Statement st= c.createStatement();
            String req="insert into evenement values ('"+p.getID_EVENT()+"','"+1+"','"+p.getID_ORGANISATEUR()+"','"+p.getNOM_EVENT()+"','"+p.getDESC_EVENT()+"','"+p.getDateEvenement()+"','"+p.getEtat()+"','"+p.getDuree()+"','"+p.getImage()+"','"+p.getType()+"','"+p.getNbrPlaces()+"','"+p.getPrix()+"')";  
            
            st.executeUpdate(req);
            
            System.out.println("l'ajout d'une evenement est effectué avec succés");  
                      
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
        
            public void modifierevenement(Evenement p){
     
        try {
            PreparedStatement pst= c.prepareStatement("update evenement set ID_USER=?,ID_ORGANIZATEUR=?,NOM_EVENT=?,DESC_EVENT=?,dateEvenement=?,etat=?,duree=?,Image=?,type=?,nbrPlaces=?,prix=? where ID_EVENT=?");
            pst.setInt(1,1);
             pst.setInt(2,1);
            
            pst.setString(3,p.getNOM_EVENT());
            pst.setString(4,p.getDESC_EVENT());
            pst.setDate(5,p.getDateEvenement());
            pst.setString(6,p.getEtat());
            pst.setString(7,p.getDuree());
            pst.setString(8,p.getImage());
            pst.setString(9,p.getType());
            pst.setInt(10,p.getNbrPlaces());
            pst.setInt(11,p.getPrix());
            pst.setInt(12,p.getID_EVENT());


            pst.executeUpdate();
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
            
       public void decrementation_nbrPlaces(int idev , int nbrPlace) {
        try{
        String req = "UPDATE `evenement` SET `nbrPlaces`=(?) WHERE `ID_EVENT`=(?)";
        PreparedStatement pstmt = c.prepareStatement(req);
        pstmt.setInt(1,nbrPlace);
        pstmt.setInt(2,idev);

         pstmt.executeUpdate();
            System.out.println("decrementation nbr place effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       
       
       public void decrEtChangementDetat(int idev) {
         try{
        String req = "UPDATE `evenement` SET `nbrPlaces`=(?) ,`etat`=(?) WHERE `ID_EVENT`=(?)";
        PreparedStatement pstmt = c.prepareStatement(req);
        pstmt.setInt(1,0);
        pstmt.setString(2,"réservé");
        pstmt.setInt(3,idev);
        

         pstmt.executeUpdate();
            System.out.println("decrementation nbr place et etat effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       
       public void incrementationNbrDePlaces(int idev , int nbrPlace) {
        try{
        String req = "UPDATE `evenement` SET `nbrPlaces`=(?) WHERE `ID_EVENT`=(?)";
        PreparedStatement pstmt = c.prepareStatement(req);
        pstmt.setInt(1,nbrPlace);
        pstmt.setInt(2,idev);

         pstmt.executeUpdate();
            System.out.println("incrementation nbr place effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       public void incEtChangementDetatDispo(int idev) {
         try{
        String req = "UPDATE `evenement` SET `nbrPlaces`=(?) ,`etat`=(?) WHERE `ID_EVENT`=(?)";
        PreparedStatement pstmt = c.prepareStatement(req);
        pstmt.setInt(1,1);
        pstmt.setString(2,"disponible");
        pstmt.setInt(3,idev);
        

         pstmt.executeUpdate();
            System.out.println("decrementation nbr place et etat effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
       
       public Evenement getEventById(int r) {
        String req = "select * from evenement where ID_EVENT = ?";
        Evenement evenement = null ; 
        try {
            PreparedStatement ps= c.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                Evenement u = new Evenement();
                  u.setID_EVENT(result.getInt(1));
                  u.setID_USER(result.getInt(2));
                  u.setID_ORGANISATEUR(result.getInt(3));
                  u.setNOM_EVENT(result.getString(4));
                  u.setDESC_EVENT(result.getString(5));
                   u.setDateEvenement(result.getDate(6));
                   u.setEtat(result.getString(7));
                   u.setDuree(result.getString(8));
                   u.setImage(result.getString(9));
                   u.setType(result.getString(10));
                   u.setNbrPlaces(result.getInt(11));
                   u.setPrix(result.getInt(12));
                   System.out.println("getEventById()"+u);
                   return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        return evenement ;
        
    }
       
       
       public  ArrayList<Evenement> findAll(){
           ArrayList<Evenement> Evenement = new ArrayList<>();
          
          try {
             Statement st= c.createStatement();
            String req="select * from  evenement";
            ResultSet result =st.executeQuery(req);
            result.beforeFirst();
            
              while (result.next()) {
                  Evenement u = new Evenement();
                  u.setID_EVENT(result.getInt(1));
                  u.setID_USER(result.getInt(2));
                  u.setID_ORGANISATEUR(result.getInt(3));
                  u.setNOM_EVENT(result.getString(4));
                  u.setDESC_EVENT(result.getString(5));
                   u.setDateEvenement(result.getDate(6));
                   u.setEtat(result.getString(7));
                   u.setDuree(result.getString(8));
                   u.setImage(result.getString(9));
                   u.setType(result.getString(10));
                   u.setNbrPlaces(result.getInt(11));
                   u.setPrix(result.getInt(12));
                   
                   
                   
                  
                  Evenement.add(u);
                  System.out.println(u);
                  
              }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
            
        }
          return Evenement;
      }
          
}
