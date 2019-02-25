/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Evaluation;
import Entite.Evenement;
import Entite.Participation;
import Services.ServiceEvaluation;
import Services.ServiceEvenement;
import Services.ServiceParticipation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author toshiba.pc
 */
public class UsereventController implements Initializable {

    @FXML
    private ListView<Evenement> listView;
    ObservableList<Evenement> data ;
    @FXML
    private Label lblId;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblDesc;
    @FXML
    private Label lblDate;
    @FXML
    private Label lbletat;
    @FXML
    private Label lblType;
    @FXML
    private Label lblDuree;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lblNbrdePlace;

    public static  Evenement selectedEvent;
    @FXML
    private ImageView imageView;
    @FXML
    private Button participer;
    @FXML
    private Button evaluer;
    @FXML
    private JFXButton AnnulerParticipation;
    @FXML
    private JFXButton retour;
    @FXML
    private Rating rat;
    @FXML
    private JFXComboBox<String> comboDate;
    @FXML
    private Label dateventlbl;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Services.ServiceEvenement es = new Services.ServiceEvenement();
       ServiceParticipation ps = new ServiceParticipation(); 
       
       
        
         setcellValue();
        
       
                  data = FXCollections.observableArrayList();
                  
                  try {
                      data = FXCollections.observableArrayList();
                       loadDataFromDatabase("Tous");
                       System.out.println(data);
                      // setcellValue();
                            listView.setCellFactory(lv -> new UsereventController.Evenements());

            } catch (SQLException ex) {
                Logger.getLogger(UsereventController.class.getName()).log(Level.SEVERE, null, ex);
            }        
                  List<String> nom = new ArrayList<String>();
                  
                  comboDate.getItems().addAll("Tous", "a venir", "Passé", "Aujourd'hui");
       comboDate.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
               /* try {

                    loadDataFromDatabase(t1);
                    setsCllTable();
                    setcellValue();
                    data = FXCollections.observableArrayList();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLConsulterController.class.getName()).log(Level.SEVERE, null, ex);
                }
                */
              String value = "a venir";
                System.out.println("ok");
               System.out.println("equals"+value.equalsIgnoreCase("a venir"));
                try {
                     loadDataFromDatabase(comboDate.getValue().toString());
                     setcellValue();
                     listView.setCellFactory(lv -> new UsereventController.Evenements());
                } catch (SQLException ex) {
                    Logger.getLogger(UsereventController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                 
                  
                  
                  
                  
            
               //   ps.participeDéja(1); // ad session id
       
    }
    
    public boolean participeDéja() {
          boolean res = false ; 
          
          Services.ServiceParticipation sp = new ServiceParticipation(); 
          List<Participation> liste = sp.participeDéja(1);
          
          for(Participation p : liste) {
              if((Integer.parseInt(lblId.getText()))==p.getIdEvenement()) {
                   res=true  ;
              } 
              
          }
         return res ;
      }
    
    public boolean EvaluerDéja() {
          boolean res = false ; 
          
          Services.ServiceEvaluation sp = new ServiceEvaluation(); 
          List<Evaluation> liste = sp.findAll();
          
          for(Evaluation p : liste) {
              if((Integer.parseInt(lblId.getText()))==p.getIdEvenement()) {
                   res=true  ;
              } 
          }
         return res ;
      } 
    
    
    
    
    
    
    private void loadDataFromDatabase(String value) throws SQLException {
       List<Evenement> evt = new ArrayList<>();
      data.clear();
           Services.ServiceEvenement se=new ServiceEvenement();
            evt = se.getallEvenement();
            if(value.equalsIgnoreCase("Tous")){
            data.clear();
                for(Evenement e : evt)
                     {
                         data.add(e); 
                     }
       
            } 
            if(value.equalsIgnoreCase("a venir")){
                data.clear();
                for(Evenement e : evt)
            {
               if(e.getDateEvenement().toLocalDate().isAfter(LocalDate.now()))   
               {
                data.add(e); 
                 }
            }
                }
            if(value.equalsIgnoreCase("Passé")){
                    data.clear();
                    for(Evenement e : evt)
                 {
               if(e.getDateEvenement().toLocalDate().isBefore(LocalDate.now()))   
               {
                data.add(e); 
                 }
                 }
                }
                if(value.equalsIgnoreCase("Aujourd'hui")){
                    data.clear();
                    for(Evenement e : evt)
                 {
                    if(e.getDateEvenement().toLocalDate().isEqual(LocalDate.now()))   
               {
                data.add(e); 
                 }
            }
                     
                }
                /*
             if(value.equalsIgnoreCase("disponible")){
                    data.clear();
                    for(Evenement e : evt)
                 {
                    if(e.getEtat().equalsIgnoreCase("disponible"))   
               {
                data.add(e); 
                 }
            }
                     
                }
            if(value.equalsIgnoreCase("réservé")){
                    data.clear();
                    for(Evenement e : evt)
                 {
                    if(e.getEtat().equalsIgnoreCase("réservé"))   
               {
                data.add(e); 
                 }
            }
                
                     
                }
                */
             listView.setItems(data);
      }
    
    private void setcellValue() {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
         Evenement eqp = listView.getItems().get(listView.getSelectionModel().getSelectedIndex());
                
             File file = new File("C:\\xampp\\htdocs\\techeventsImg\\",eqp.getImage());
             Image image = new Image(file.toURI().toString());
             Services.ServiceEvaluation ess = new ServiceEvaluation();
              float moy = ess.moyByName(eqp.getID_EVENT()); 
                            rat.setRating(moy);
                            rat.setDisable(true);
         
        
        lblId.setText(Integer.toString(eqp.getID_EVENT()));
         lblnom.setText(eqp.getNOM_EVENT());
         lblDate.setText(eqp.getDateEvenement().toString());
         dateventlbl.setText(eqp.getDateEvenement().toString());
         lblDesc.setText(eqp.getDESC_EVENT());
         lbletat.setText(eqp.getEtat());
         lblDuree.setText(eqp.getDuree());
         lblType.setText(eqp.getType());
                System.out.println("Evenet:"+eqp.getDateEvenement());
         
         
         lblDate.setText(eqp.getEtat());
         imageView.setImage(image);
                  
         lblNbrdePlace.setText(Integer.toString(eqp.getNbrPlaces()));
         lblPrix.setText(Float.toString(eqp.getPrix()));
         
          System.out.println("Evaluer Deja :"+EvaluerDéja());
         
        if(participeDéja()==true) {
           
           participer.setDisable(true);
           participer.setText("je participe Déja");


         }
         else {
             participer.setText("Participer");
             participer.setDisable(false);
         }
        
        
      //  Services.ServiceEvenement ess = new ServiceEvenement(); 
   //     Double moy = ess.moyByName(eqp.getIdEvenement());
        
       /*  rat.setRating(moy);
         rat.setPartialRating(true);
        */
         
            
       /* Image image2 = new Image(file.toURI().toString());
            
            imgg.setImage(image2); 
            imgg.setFitHeight(400);
            imgg.setFitHeight(200);
         */
            
            
            }
        });
    } 

    @FXML
    private void participeraction(ActionEvent event) throws SQLException, IOException, NexmoClientException {
        Services.ServiceParticipation sp = new ServiceParticipation();
        ServiceEvenement se = new ServiceEvenement(); 
        int idEve = Integer.parseInt(lblId.getText());
        int nbrPlaces = Integer.parseInt(lblNbrdePlace.getText()); 
        if(nbrPlaces==1){
        se.decrEtChangementDetat(idEve);
        Participation p = new Participation(); 
        p.setIdEvenement(idEve);
        sp.ajouterparticipation(p);
        System.out.println("participation ajouté & changement d'etat");
        }else{
        Participation p = new Participation(); 
        p.setIdEvenement(idEve);
        sp.ajouterparticipation(p);
        se.decrementation_nbrPlaces(idEve, (Integer.parseInt(lblNbrdePlace.getText())-1));
        //send sms api : 
      NexmoClient client = new NexmoClient.Builder()
                    .apiKey("ce311464")
                    .apiSecret("0uEI7ephKIDVDG3m")
                    .build();
            
            String messageText = "Hello from Nexmo, Paricipation confirmé";
            TextMessage message = new TextMessage("Nexmo", "21651804334", messageText);
            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
            System.out.println("Send message okay");

            for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                System.out.println(responseMessage);
            }
             
               // showAlert(Alert.AlertType.INFORMATION, "Form Confirmation!", "Participation Ajouté!");
              // Alert alert = new Alert(AlertType.ERROR, "content text");
                //alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                data.clear();
                loadDataFromDatabase("Tous");
                System.out.println("participation ajouté");
                Stage stage = new Stage();
             ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("userevent.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        
    /*    Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Test Connection");
        alert.setHeaderText("Results:");
        alert.setContentText("Connect to the database successfully!");
         alert.showAndWait();
        */
    }
    
     private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void evalueraction(ActionEvent event) throws IOException {
        ServiceEvenement us = new ServiceEvenement();
        Evenement.setInstance(us.getEventById(Integer.parseInt(lblId.getText())));
        System.out.println("Evt connecté"+Evenement.getInstance());
        Stage stage = new Stage();
             ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("EvaluationFxml.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void AnnulerParticipationAction(ActionEvent event) throws IOException {
        
        ServiceEvenement us = new ServiceEvenement();
        Evenement.setInstance(us.getEventById(Integer.parseInt(lblId.getText())));
        System.out.println("Evt connecté"+Evenement.getInstance());
        Stage stage = new Stage();
             ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("AnnulerParticipation.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        
    }

    @FXML
    private void retouraction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("liaison.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    
    //declarer list view 
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   static public class Evenements extends ListCell<Evenement> { 
  
   
  
    public Evenements(){ 
        
    } 
  
  
    @Override
    protected void updateItem(Evenement item, boolean bln) { 
         super.updateItem(item, bln);
                       Rating rss = new Rating();
                       rss.setPartialRating(true);
                       // Button delete =new Button("supprimer");
                        // Button update =new Button("modifier");
                      Services.ServiceEvaluation ess = new ServiceEvaluation(); 
                      //double moy = ess.moyByName(item.getID_EVENT()); 
                            //rss.getStylesheets().add("GUI/fxml1.css");
                           
                        if (item != null) {
                            Text t =new Text(item.getNOM_EVENT().toString());
                            Text t2 =new Text(item.getDateEvenement().toString());
                            Text t3 =new Text(item.getDESC_EVENT());
                            Text t4 = new Text(item.getEtat());
                          /*  if(item.getEtat().toLowerCase().equals("réservé")){
                            t4.setStyle("-fx-text-fill: red;");
                             }*/
                            t.setStyle("-fx-font-size: 23 arial;");
                            t2.setStyle("-fx-font-size: 17 arial;");
                            t3.setStyle("-fx-font-size: 17 arial;");
                            t4.setStyle("-fx-font-size: 17 arial;");
                                  
                                
                   
                    
                    
                            
                       //   Button facebook = new Button("Partager");
                           
                            
                           float moy = ess.moyByName(item.getID_EVENT()); 
                            rss.setRating(moy);
                            
                            VBox vBox = new VBox(t,t2,t3,t4,rss);
                             File file = new File("C:\\xampp\\htdocs\\techeventsImg\\",item.getImage());
            
                            Image image = new Image(file.toURI().toString());
                            ImageView img = new ImageView(image); 
                            img.setFitHeight(120);
                            img.setFitWidth(190);
                            
                            HBox hBox = new HBox(img,vBox);
                            
                           
                        /* facebook.setOnAction(e->{
                                String accesToken = "EAACEdEose0cBAFsAbMfb6eFz3ZCPJqOFTOmuRJ1Q9PWqwmV3nNKuyzeWwPd3nWKXeL6ZB2DjF8Fv5yHOsrNMw72IhFYHZC4ZBXqXP64dD6zQDbRJ85LZCsZCS5noBcSVmxZCyKYNteubCuZB4TjiRGcbroFUeKDZAjNKsokWkNe65EYKaeBlyQvwXS2p3PFXoKWcZD" ;
                                 FacebookClient fb = new DefaultFacebookClient(accesToken);
                    
                               
                              FacebookType response =  fb.publish("fb.com/",FacebookType.class,Parameter.with(accesToken,ListCell.class));
                                System.out.println(response);
                    
                           
                            }); 
                            */
                            hBox.setSpacing(10);
                            setGraphic(hBox);

    } 
}   }
    
    
    
    
}
