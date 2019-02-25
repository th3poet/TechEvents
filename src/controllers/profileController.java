/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Users;
import interfaces.IUsers;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import services.userservices;
import util.BCrypt;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class profileController implements Initializable {

    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXPasswordField txtpasswod;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private ImageView profileimg;
    @FXML
    private Label lblstatus;
    @FXML
    private JFXButton btnupdate;
    
    String getImageUrl,mail;
    @FXML
    private Label lblemail;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    void setUserInfo(String mail) {
    
            if (mail == null) {
                return;
            }
            IUsers u1 = new userservices();
            Users user = u1.findByMail(mail); 
            txtlastname.setText(user.getFIRST_NAME());
            txtfirstname.setText(user.getLAST_NAME());
            lblemail.setText(mail);
            getImageUrl = user.getPROFILE_PHOTO();
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            profileimg.setImage(ima);     
    }    
    
    @FXML
     void updateaccount(MouseEvent event) {
         
        IUsers u1 = new userservices();
        Users user = u1.findByMail(lblemail.getText());
        if (txtlastname != null){
                user.setFIRST_NAME(txtlastname.getText());}
        if (txtfirstname != null){
                user.setLAST_NAME(txtfirstname.getText());}
        if (txtpasswod != null){
                String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
                user.setPASSWORD(hashed);
                }
        
        user.setPROFILE_PHOTO(getImageUrl);   
        u1.update(user);
        setUserInfo(mail);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setContentText("Votre compte a été modifier avec succès.");
                alert.showAndWait();
    }
    
    
       
    
}
