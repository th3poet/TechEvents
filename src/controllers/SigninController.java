/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Users;
import interfaces.IUsers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.userservices;
import util.BCrypt;

/**
 *
 * @author thepoet
 */
public class SigninController implements Initializable {

    @FXML
    private JFXButton btngocreateaccount;
    @FXML
    private ImageView signinimgprog;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private AnchorPane signinPane;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private Label labelerror;
    private SigninController globalCntrl;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidationlogin();
        signinimgprog.setVisible(false);
    }    

    @FXML
    private void opencreateaccountaction(MouseEvent event) {
        
        btngocreateaccount.getScene().getWindow().hide();
        try {
            signinimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Signup.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
 
        IUsers user = new userservices();
    @FXML
    private void loginaccountaction(MouseEvent event) {
        
        
        Users u1 = user.findByMail(txtemail.getText());
        if (txtemail.getText().isEmpty() || txtpassword.getText().isEmpty()) {
            labelerror.setTextFill(Color.TOMATO);
            labelerror.setText("Veuillez remplir les champs obligatoires");}
        else {
        labelerror.setText("");    
        signinimgprog.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            if (txtemail.getText().equals("admin@esprit.tn") && txtpassword.getText().equals("admin123")) {
                LoginAdmin();
            }
            
            else if (u1 == null) {
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Compte n'existe pas");
                signinimgprog.setVisible(false);}
            
            else if (BCrypt.checkpw(txtpassword.getText(),u1.getPASSWORD())) {
                    if (u1.getSTATUS() == 0) {
                       signinimgprog.setVisible(false);
                       labelerror.setText("Votre compte n'est pas encore activée");}
                    else
                       LoginUser();}
            
            else {
                signinimgprog.setVisible(false);
                labelerror.setTextFill(Color.TOMATO);
                labelerror.setText("Veuillez vérifier vos paramétres");
                 }

        });   
        pauseTransition.play();
      }
    }
    private void LoginUser() {
        btnsignin.getScene().getWindow().hide();
        try {
            signinimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard_User.fxml"));
            Parent root = loader.load();
            Dashboard_UserController dashboard_UserController = loader.getController();
            dashboard_UserController.transferMessage(txtemail.getText());
            dashboard_UserController.createPages(txtemail.getText());
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void LoginAdmin() {
        btnsignin.getScene().getWindow().hide();
        try {
            signinPane.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard_Admin.fxml"));
            Parent root = loader.load();
            Dashboard_AdminController dashboard_AdminController = loader.getController();
            dashboard_AdminController.transferMessage(txtemail.getText());
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    private void handleValidationlogin() {
        RequiredFieldValidator fieldValidator1 = new RequiredFieldValidator();
        fieldValidator1.setMessage("Champ Obligatoire");
        fieldValidator1.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtemail.getValidators().add(fieldValidator1);
        txtemail.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal1) -> {
            if (!newVal1) {
                txtemail.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Champ Obligatoire");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtpassword.getValidators().add(fieldValidator2);
        txtpassword.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal4) -> {
            if (!newVal4) {
                txtpassword.validate();

            }
        });

    }
    
  
    

}
