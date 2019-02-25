/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evaluation;
import Services.ServiceEvaluation;
import Services.ServiceEvenement;
import Services.ServiceParticipation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class LiaisonController implements Initializable {

    @FXML
    private Button ticket;
    @FXML
    private Button event;
    @FXML
    private Button jeparticipe;
    
    FileChooser fc = new FileChooser () ;
     File selectedFile ; 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
     /*   Services.ServiceEvaluation us ;
        us = new ServiceEvaluation();
        Evaluation ev = new Evaluation();
        ev = new Evaluation(1, 1, 2);
      //  us.ajouterEvaluation(ev);
        //us.findAll();
        us.evalueDéja(1);
        Services.ServiceParticipation es = new ServiceParticipation();
     es.supprimerParticipation(17,1);
*//*
     Services.ServiceEvenement se = new ServiceEvenement();
     se.incEtChangementDetatDispo(1);
     System.out.println("lblnombre:"+1);
*/
     
     
    }    

    @FXML
    private void ticket(ActionEvent event) throws IOException {
         Parent home_page_parent =FXMLLoader.load(getClass().getResource("G_ticket.fxml"));
       Scene home_page_scene =new Scene (home_page_parent);
       Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
        
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
        Parent home_page_parent =FXMLLoader.load(getClass().getResource("G_event.fxml"));
       Scene home_page_scene =new Scene (home_page_parent);
       Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
         Parent home_page_parent =FXMLLoader.load(getClass().getResource("afficher_reservation.fxml"));
       Scene home_page_scene =new Scene (home_page_parent);
       Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
       app_stage.setScene(home_page_scene);
       app_stage.show();
    }

    @FXML
    private void participer(ActionEvent event)  throws IOException {
       Parent home_page_parent =FXMLLoader.load(getClass().getResource("userevent.fxml"));
       Scene home_page_scene =new Scene (home_page_parent);
       Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
      // app_stage.setWidth(200);
       app_stage.setScene(home_page_scene);
       app_stage.show();
    
    }
    
}
