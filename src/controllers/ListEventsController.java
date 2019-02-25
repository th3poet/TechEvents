/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author thepoet
 */
public class ListEventsController implements Initializable {

    @FXML
    private AnchorPane holderAnchor;
    @FXML
    private JFXRadioButton RDFirstName;
    @FXML
    private ToggleGroup filter;
    @FXML
    private JFXRadioButton RDMail;
    @FXML
    private JFXTextField inputSearch;
    @FXML
    private JFXButton Search;
    @FXML
    private TableView<?> tableUsers;
    @FXML
    private TableColumn<?, ?> IDUser;
    @FXML
    private TableColumn<?, ?> lastname;
    @FXML
    private TableColumn<?, ?> firstname;
    @FXML
    private TableColumn<?, ?> Mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void buildUsersTable() {
    }
    
}
