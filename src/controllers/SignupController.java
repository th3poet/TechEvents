/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import entities.Users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.userservices;
import interfaces.IUsers;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import util.BCrypt;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class SignupController implements Initializable {

    @FXML
    private JFXButton btnbackconnect;
    @FXML
    private AnchorPane signupPane;
    @FXML
    private ImageView signupimgprog;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXPasswordField txtpasswod;
    @FXML
    private JFXButton btncreate;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private ImageView profileimg;

    private Connection connection;
    private PreparedStatement pst;
    String getImageUrl="src/images/profile.png";
    @FXML
    private Label lblstatus;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handleValidation();
        signupimgprog.setVisible(false);
        try {
            profileimg.setImage(new Image(new FileInputStream("src/images/profile.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void openloginscreenaction(MouseEvent event) {
        btnbackconnect.getScene().getWindow().hide();
        try {
            signupimgprog.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Signin.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void choosephoto(MouseEvent event) {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        getImageUrl = selectedfile.getAbsolutePath();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        profileimg.setImage(ima);
    }
    
    
    @FXML
    private void createaccountaction(MouseEvent event) {
        
       IUsers user = new userservices();
       
       String Email = txtemail.getText()+"@esprit.tn";
       Users u1 = user.findByMail(Email);
       String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
       Users u = new Users(txtfirstname.getText(), txtlastname.getText(), Email,hashed,getImageUrl);
       if (u1 != null) {
                lblstatus.setTextFill(Color.TOMATO);
                lblstatus.setText("Compte Existant");
            } 
       else if (u1 == null){ 
         if (txtfirstname.getText().isEmpty() || txtlastname.getText().isEmpty() || txtemail.getText().isEmpty() || txtpasswod.getText().isEmpty()) {
            lblstatus.setTextFill(Color.TOMATO);
            lblstatus.setText("Veuillez remplir les champs obligatoires");
            }
         else {
                lblstatus.setText("");
                signupimgprog.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(3));
                pauseTransition.setOnFinished(ev -> {
                user.add(u);
                btncreate.getScene().getWindow().hide();
                try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Signin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 });   
        pauseTransition.play();
        Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Votre compte a été crée avec succès. Vous recevrez un email indiquant l'activation de votre compte pour pouvoir vous connecter. ");
                alert.showAndWait();
              }
                        
         }  
         
    }
    
    private void handleValidation() {
        RequiredFieldValidator fieldValidator = new RequiredFieldValidator();
        fieldValidator.setMessage("Champ Obligatoire");
        fieldValidator.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtfirstname.getValidators().add(fieldValidator);
        txtfirstname.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal) -> {
            if (!newVal) {
                txtfirstname.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator2 = new RequiredFieldValidator();
        fieldValidator2.setMessage("Champ Obligatoire");
        fieldValidator2.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtlastname.getValidators().add(fieldValidator2);
        txtlastname.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal2) -> {
            if (!newVal2) {
                txtlastname.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator3 = new RequiredFieldValidator();
        fieldValidator3.setMessage("Champ Obligatoire");
        fieldValidator3.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtemail.getValidators().add(fieldValidator3);
        txtemail.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal3) -> {
            if (!newVal3) {
                txtemail.validate();

            }
        });
        
        RequiredFieldValidator fieldValidator4 = new RequiredFieldValidator();
        fieldValidator4.setMessage("Champ Obligatoire");
        fieldValidator4.setIcon(new FontAwesomeIconView(FontAwesomeIcon.TIMES));
        txtpasswod.getValidators().add(fieldValidator4);
        txtpasswod.focusedProperty().addListener((ObservableValue<? extends Boolean> o, Boolean oldVal, Boolean newVal4) -> {
            if (!newVal4) {
                txtpasswod.validate();

            }
        });
    }
}
