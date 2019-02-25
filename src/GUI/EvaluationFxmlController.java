/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evaluation;
import Entite.Evenement;
import Services.ServiceEvaluation;
import Services.ServiceEvenement;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author  toshiba.pc

 */
public class EvaluationFxmlController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblDuree;
    @FXML
    private Label lblEtat;
    @FXML
    private Label lblType;
    @FXML
    private Label lblnombre;
    @FXML
    private Label lblPrix;
    @FXML
    private JFXButton evaluer;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Evt courant"+Evenement.getInstance());
        lblId.setText(Integer.toString(Evenement.getInstance().getID_EVENT()));
        lblNom.setText(Evenement.getInstance().getNOM_EVENT());
        lblDate.setText(Evenement.getInstance().getDateEvenement().toString());
        lblDesc.setText(Evenement.getInstance().getDESC_EVENT());
        lblEtat.setText(Evenement.getInstance().getDESC_EVENT());
        lblDuree.setText(Evenement.getInstance().getDuree());
        lblType.setText(Evenement.getInstance().getType());
        lblnombre.setText(Integer.toString(Evenement.getInstance().getNbrPlaces()));
        lblPrix.setText(Integer.toString(Evenement.getInstance().getPrix()));
        rating.setUpdateOnHover(true);
        
       
        
        
        
    }    
    
   
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void evaluerAction(ActionEvent event) throws IOException {
        //get Rating value
         float note = (float) rating.getRating();
         Services.ServiceEvaluation us = new ServiceEvaluation();
         Evaluation ev = new Evaluation();
         ev.setIdEvenement(Integer.parseInt(lblId.getText()));
         ev.setIdUser(1);
         ev.setNoteEvenement(note);
         us.ajouterEvaluation(ev);
         System.out.println("evaluation:"+note);
          
      //   showAlert(Alert.AlertType.INFORMATION, "Form Confirmation!", "MERCi pour votre évaluation!");
      
                Stage stage = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("userevent.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

    @FXML
    private void retourAction(ActionEvent event) throws IOException {
        
         Stage stage = new Stage();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("userevent.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    
}
