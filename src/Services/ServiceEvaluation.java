/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import  dataSource.connexion;
import Entite.Evaluation;
import Entite.Evenement;
import Entite.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author toshiba.pc
 */
public class ServiceEvaluation {
    
     private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public ServiceEvaluation() {
        connection = connexion.getInstance().getCnx();
    }
    
             
     
        public void ajouterEvaluation(Evaluation r) {

       try {
          String requete = "insert into `evaluation` (ID_USER,ID_EVENT,note) values (?,?,?)";

            ps = connection.prepareStatement(requete);
           // ps.setInt(1, r.getEvaluationId());
           // ps.setInt(1,r.getIdUser());
            ps.setInt(1,r.getIdUser());
            ps.setInt(2, r.getIdEvenement());
            ps.setFloat(3, r.getNoteEvenement());
            
            
            
            System.out.println("Evaluation ajouté");
             
               
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Services.ServiceEvaluation.class.getName()).log(Level.SEVERE, null, ex);

        }
        }
       
        
       public List<Evaluation> findAll()
        {
        List<Evaluation> listeEvenement = new ArrayList<>();

        String requete = "select * from evaluation ";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
               Evaluation f = new Evaluation();
               f.setEvaluationId(r.getInt("ID_EVAL"));
               f.setIdEvenement(r.getInt("ID_EVENT"));
               f.setIdUser(r.getInt("ID_USER"));
               f.setNoteEvenement(r.getFloat("note"));
               
              
               System.out.println(f.toString());
                
               
                listeEvenement.add(f);
            }
            return listeEvenement;
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        }
        
       
      
        public float moyByName(int id)
        {
        List<Evaluation> listeEvenement = new ArrayList<>();
        float sum = 0 ; 
        float moy =0 ;

        
      Services.ServiceEvaluation se=new Services.ServiceEvaluation();
        List<Evaluation> eqs = new ArrayList<>();

      eqs = se.findAll().stream().filter(e->e.getIdEvenement()==id).collect(Collectors.toList());
           
            for(Evaluation e : eqs) {
                sum = sum + e.getNoteEvenement();
                
            }
            System.out.println("recuperation de la moyenne par id "+eqs.size());
          return  moy = sum/eqs.size(); 
        
   }
       
        
      
    public List<Evaluation> evalueDéja(int id) {
        List<Evaluation> listeEvaluation = new ArrayList<>();

        String requete = "select * from evaluation WHERE idUser = "+id; 
                
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Evaluation f = new Evaluation();
                
               
               f.setEvaluationId(r.getInt(1));
               f.setEvaluationId(r.getInt(2));
               f.setEvaluationId(r.getInt(3));
               f.setEvaluationId(r.getInt(4));
               listeEvaluation.add(f);
                
                System.out.println("recupération de evaluation by id evenement "+listeEvaluation.toString());
            }
            return listeEvaluation;
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
 }

    }

    

