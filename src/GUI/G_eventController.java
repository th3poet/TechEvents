 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entite.Evenement;

import Services.ServiceEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.TextFields;
import sun.security.pkcs11.P11Util;

/**
 * FXML Controller class
 *
 * @author toshipa.pc
 */
public class G_eventController implements Initializable {
   ServiceEvenement us =new ServiceEvenement();
    private ObservableList<Evenement> data;
    
    @FXML
    private TableColumn<Evenement, String> affnomevent;
    @FXML
    private TableColumn<Evenement, String> affDesc;
    @FXML
    private TableColumn<Evenement, Date> affdateevent;
    @FXML
    private TableColumn<Evenement, String> affetat;
    @FXML
    private TableColumn<Evenement, String> affduree;
    @FXML
    private TableColumn<Evenement, String> afftypes;
    @FXML
    private TableColumn<Evenement, Integer> affnbrplace;
    @FXML
    private TableColumn<Evenement, Integer> affprix;
    @FXML
    private TableView<Evenement> tableviewevnement;
    @FXML
    private TextField textfieldnom;
    @FXML
    private ComboBox<String> textfieldtype;
   @FXML
    private JFXTextField textfielfdesc;
    @FXML
    private TextField textfieldetat;
    @FXML
    private JFXTextField textfieldplace;
    @FXML
    private TextField textfieldprix;
    @FXML
    private DatePicker dateajou;
    @FXML
    private ComboBox<String> textfieldduree;
    @FXML
    private TextField textfieldimage;
    @FXML
    private TableColumn<Evenement, String> affimage;
    private TextField motype;
    private TextField modesc;
    private TextField moduree;
    private TextField monbr;
    private TextField moprix;
    private TextField monom;
    private DatePicker modate;
    private TextField moID;
    private TextField modimage;
    private TextField moetat;
    @FXML
    private TextField rech;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textdesc;
    @FXML
    private JFXTextField textetat;
   @FXML
    private JFXComboBox<String> textduree;
    @FXML
    private TextField textImage;
    @FXML
    private JFXComboBox<String> texttype;
    @FXML
    private TextField textnbrplaces;
    @FXML
    private TextField textprix;
    @FXML
    private Label idModif;
    @FXML
    private Button Modifier;
    private DatePicker dateee;
    @FXML
    private DatePicker textDat;
    
     String msgTitle = "" ;
     String msgContent = "" ;
    
    FileChooser fc = new FileChooser () ;
    FileChooser fc1 = new FileChooser () ;
    @FXML
    private JFXButton chooserfile;
    File selectedFile ; 
    @FXML
    private ImageView imgView;
    @FXML
    private Label lblimage;
    @FXML
    private JFXButton filechooser;
    @FXML
    private ImageView img;
    @FXML
    private Label lilimage1;
    @FXML
    private Label lblerror;
    @FXML
    private Label lblerrormodif;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private JFXTextField textfielforganis;
    @FXML
    private JFXTextField textfielorganis;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          data = FXCollections.observableArrayList();
          loadDataFromDatabase();
          search();
          setsCllTable();
          setcellValue();
          textfieldetat.setText("disponible");
          textfieldetat.setDisable(true);
          LocalDate localDate = LocalDate.now();
          dateajou.setValue(localDate);
          textfieldduree.getItems().addAll("Quelque heures","une journée","Plus Q'une journée");
          textfieldtype.getItems().addAll("technologique","culturelle","scientifique");
          
          textduree.getItems().addAll("Quelque heures","une journée","Plus Q'une journée");
          texttype.getItems().addAll("technologique","culturelle","scientifique");
          //blocage du clavier numerique
           textfieldprix.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
           textfieldplace.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation());
           //autocomplete text 
           List<String> nom = new ArrayList<String>();
           ArrayList<Evenement> evenements = new ArrayList<>();
           
           ServiceEvenement se = new ServiceEvenement();
           
           evenements = (ArrayList<Evenement>) se.findAll();
            for (Evenement usr : evenements) {
              nom.add(usr.getNOM_EVENT()) ;
              }
            //autocomplete : 
            TextFields.bindAutoCompletion(rech, nom);
            deletebtn.setDisable(true);
            Modifier.setDisable(true);
            filechooser.setDisable(true);
            
            
          
          
          
          
    }    
    
     public void setsCllTable() {
        affnomevent.setCellValueFactory(new PropertyValueFactory<>("NOM_EVENT"));
        affDesc.setCellValueFactory(new PropertyValueFactory<>("DESC_EVENT"));
        affduree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
       affdateevent.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        affprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        afftypes.setCellValueFactory(new PropertyValueFactory<>("type"));
        affetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        affnbrplace.setCellValueFactory(new PropertyValueFactory<>("nbrPlaces"));
        affimage.setCellValueFactory(new PropertyValueFactory<>("Image"));
       
        
    }
    
    private void loadDataFromDatabase() {
      
      Services.ServiceEvenement es = new ServiceEvenement(); 
      List <Evenement> evs = es.getallEvenement();
      
      for(Evenement ev : evs )
      {
          data.add(ev);
      }
        tableviewevnement.setItems(data);
    }
    
    
    
    private void setcellValue() {
        tableviewevnement.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Evenement pl = tableviewevnement.getItems().get(tableviewevnement.getSelectionModel().getSelectedIndex());
                idModif.setText(Integer.toString(pl.getID_EVENT()));
                textnom.setText(pl.getNOM_EVENT());
               textDat.setValue(pl.getDateEvenement().toLocalDate());
                textdesc.setText((pl.getDESC_EVENT()));
                textduree.setValue(pl.getDuree().toString());
                textetat.setText("disponible");
                textnbrplaces.setText(Integer.toString(pl.getNbrPlaces()));
                textprix.setText(Integer.toString(pl.getPrix()));
                texttype.setValue(pl.getType().toString()) ; 
                textnbrplaces.setText(Integer.toString(pl.getNbrPlaces()));
                textImage.setText(pl.getImage());
              /*  lbluser.setText(Integer.toString(pl.getId_user()));
                jmgsgs.setText(pl.getImage());
                image ();
                participer.setDisable(false);
                */
              java.sql.Date dateev = java.sql.Date.valueOf(pl.getDateEvenement().toLocalDate());
                
               File file = new File("C:\\xampp\\htdocs\\techeventsImg\\",pl.getImage());
             Image image = new Image(file.toURI().toString());
                img.setImage(image);
            
                
                
                
                Modifier.setDisable(false);
                filechooser.setDisable(false);
                deletebtn.setDisable(false);
             /*   anchor.setDisable(false);*/
                
                
                
            }
        });
    }

    
     public void search() {
        FilteredList<Evenement> filterdata = new FilteredList<>(data, e -> true);
        rech.setOnKeyReleased(e -> {
            rech.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filterdata.setPredicate((Predicate<? super Evenement>) evenement -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    if ((evenement.getNOM_EVENT().contains(newValue)) || (evenement.getNOM_EVENT().toLowerCase().contains(newValue))) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Evenement> sorteddata = new SortedList<>(filterdata);
            sorteddata.comparatorProperty().bind(tableviewevnement.comparatorProperty());
            tableviewevnement.setItems(sorteddata);
        });
    }

    @FXML
    private void clickafficher(ActionEvent event) {
        
     /*   affnomevent.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NOM_EVENT"));
       affDesc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("DESC_EVENT"));
        affdateevent.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("dateEvenement"));
        affetat.setCellValueFactory(new PropertyValueFactory<Evenement, String>("etat"));
        affduree.setCellValueFactory(new PropertyValueFactory<Evenement, String>("duree"));
        affimage.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Image"));
        afftypes.setCellValueFactory(new PropertyValueFactory<Evenement, String>("type"));
         affnbrplace.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbrPlaces"));
          affprix.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("prix"));

           tableviewevnement.setItems((ObservableList<Evenement>) us.getallEvenement());
        */
    }
     private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void ajouterevent(ActionEvent event) throws IOException {
        
        /* Evenement P =new Evenement(textfieldnom.getText(),dateajou.getValue(),textfielfdesc.getText(),textfieldetat.getText(),textfieldduree.getText(),textfieldimage.getText(),textfieldtype.getText(),textfieldplace.getText(),textfieldprix.getText());
    ServiceEvenement serv =new ServiceEvenement();
    serv.ajouterevenement(P);*/
        if((validationChampsAjout()==false)||(isvalidDate(java.sql.Date.valueOf(dateajou.getValue())))){
            
      lblerror.setText(msgContent);
            System.out.println("erreur de validation");
            
            //fonction de verification si une evt a une date existaante
            if((isvalidDate(java.sql.Date.valueOf(dateajou.getValue())))){
                    //     showAlert(Alert.AlertType.ERROR, "Form Error !", "il existe un évenement a cette date!");
                    lblerror.setText("Evenement existant a cette date");
                
            }
        
        }else{  
        
         String nomEvenement = textfieldnom.getText() ; 
        
        String Description = textfielfdesc.getText();
        LocalDate dateEvenement=dateajou.getValue(); 
        String etat = textfieldetat.getText(); 
                String dureeEvenement=textfieldduree.getValue().toString(); 

        String Image = textfieldimage.getText(); 
        String typeEvenement = textfieldtype.getValue().toString(); 
        int nbrPlaces = Integer.parseInt(textfieldplace.getText());
        int Idorganisateur = Integer.parseInt(textfielorganis.getText());
        int prixEvenement = Integer.parseInt(textfieldprix.getText());
      java.sql.Date dateev = java.sql.Date.valueOf(dateEvenement);
        
        
Evenement ev = new Evenement();
ev.setID_ORGANISATEUR(Idorganisateur);
ev.setNOM_EVENT(nomEvenement);
ev.setDESC_EVENT(Description);
ev.setDateEvenement(dateev);
ev.setEtat(etat);
ev.setDuree(dureeEvenement);
ev.setImage(Image);
ev.setType(typeEvenement);

ev.setNbrPlaces(nbrPlaces);
ev.setPrix(prixEvenement);


        
        Services.ServiceEvenement es = new ServiceEvenement(); 
        es.ajouterevenement(ev);
         final String username = "dhiabsdl94@gmail.com";
		final String password = "D58059894";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dhiabsdl94@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("ameny.ferchchichi@gmail.com"));
			message.setSubject("Inscription fini ");
			message.setText("L'evenement,"+nomEvenement
				+ "\n\n a la date!:"+dateEvenement+"est ajouté avec succées" );

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        if(isvalidDate(ev.getDateEvenement())) System.out.println("Oki");
        // showAlert(Alert.AlertType.INFORMATION, "Form information !", "Even added successfully!");
         
         Stage stage = new Stage();
          ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("G_event.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("msgTitle");
             alert.setContentText("msgContent");
             alert.showAndWait();
        */
        }
    }

    @FXML
    private void deleteevent(ActionEvent event) {
//        Integer id = Integer.parseInt(moID.getText()) ; 
                 Integer id = Integer.parseInt(idModif.getText()) ; 
 //Integer id = Integer.parseInt(idModif.getText()) ; 
        
        
        //int idE = Integer.valueOf(idEve.getText());
         System.out.println("suppression id:"+id);
         //  Date datefe = (Date.valueOf(date.getValue()));
         Services.ServiceEvenement ser = new ServiceEvenement();
         ser.supprimerEvenement(id);
        /*String url = "jdbc:mysql://localhost:3306/russia2018";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        String sql = ("Delete From `evenement`  WHERE `evenement`.`idEvenement` = (?)");
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idE);*/
        
        idModif.setText("");
        textnom.setText("");
        textDat.setValue(null);
        textetat.setText("");
        texttype.setValue("");
        textduree.setValue("");
        textnbrplaces.setText("");
        texttype.setValue("");
        textprix.setText("") ; 
        textImage.setText("");
        
        data.clear();
        loadDataFromDatabase();
        lblerrormodif.setText("Evenement supprimé");
        
  

    }

    private void modifier(ActionEvent event) throws IOException {
        
         if((isvalidDate(java.sql.Date.valueOf(dateajou.getValue())))){
                         showAlert(Alert.AlertType.ERROR, "Form Error !", "il existe un évenement a cette date!");

                
            }
        
        else{  
        Integer id = Integer.parseInt(moID.getText()) ; 
        
        String nom = monom.getText();
        String desc=modesc.getText(); 
        LocalDate dateEvenement=modate.getValue(); 
        String etat=moetat.getText();
        String duree = moduree.getText(); 
        String image=modimage.getText();
        String type = motype.getText(); 
        
                
         

   
       
        
        int nbrPlaces = Integer.parseInt(monbr.getText());
        int prixEvenement = Integer.parseInt(moprix.getText());
            java.sql.Date dateev = java.sql.Date.valueOf(dateEvenement);

        Evenement ev = new Evenement();
ev.setID_EVENT(id);
 ev.setNOM_EVENT(nom);
ev.setDESC_EVENT(desc);
ev.setDateEvenement(dateev);
ev.setEtat(etat);
ev.setDuree(duree);
ev.setImage(image);
ev.setType(type);


ev.setNbrPlaces(nbrPlaces);
ev.setPrix(prixEvenement);
ServiceEvenement v=new ServiceEvenement();
v.modifierevenement(ev);
Services.ServiceEvenement es = new ServiceEvenement(); 
        es.ajouterevenement(ev);
         final String username = "dhiabsdl94@gmail.com";
		final String password = "D58059894";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dhiabsdl94@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("ameny.ferchchichi@gmail.com"));
			message.setSubject("evenement modifier ");
			message.setText("L'evenement est modifier" );

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
     /*   if(isvalidDate(ev.getDateEvenement())) System.out.println("Oki");
         showAlert(Alert.AlertType.INFORMATION, "Form information !", "Even added successfully!");
                */
         Stage stage = new Stage();
          ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("G_event.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("msgTitle");
             alert.setContentText("msgContent");
             alert.showAndWait();
        */
       
}
       
}
    //valide dte ou non 
    public boolean isvalidDate(Date date) {
        boolean res = false ; 
        Services.ServiceEvenement es = new ServiceEvenement(); 
      List <Evenement> evs = es.getallEvenement();
      
        for (Evenement ev : evs) {
            if(ev.getDateEvenement().compareTo(date)==0) 
            {
                res=true ; 
                
            }
            
        }
      
      System.out.println("isvalid date:"+res);
      return res ; 
     }

    @FXML
    private void modifierevent(ActionEvent event) throws IOException {
        if(((validationChampsModifier()==false))){
                    //showAlert(Alert.AlertType.ERROR, "Form Error !", "il existe un évenement a cette date!");

                lblerrormodif.setText(msgContent);
                System.out.println("eeee");
            }
        
        else{  
         Integer id = Integer.parseInt(idModif.getText()) ; 
        
        String nom = textnom.getText();
        String desc=textdesc.getText(); 
         LocalDate dateEvenement=textDat.getValue();
        
        String etat=textetat.getText();
        String duree = textduree.getValue().toString(); 
        String image=textImage.getText();
        String type = texttype.getValue().toString(); 
        int nbplace=Integer.parseInt(textnbrplaces.getText());
        
        
        int prix = Integer.parseInt(textprix.getText());
        java.sql.Date dateev = java.sql.Date.valueOf(dateEvenement);
        
        
        
        Evenement p = new Evenement();
        p.setNbrPlaces(nbplace);
        p.setDESC_EVENT(desc);
        p.setDuree(duree);
        p.setType(type);
        p.setPrix(prix);
        p.setEtat(etat);
        p.setDateEvenement(dateev);
        p.setImage(image);
        p.setID_USER(1);
        p.setID_ORGANISATEUR(1);
        p.setNOM_EVENT(nom);
        p.setID_EVENT(id);
        System.out.println("modif");
        
        us.modifierevenement(p);
    /*    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();*/
      //  tableviewevnement.r;
      
       data.clear();
       loadDataFromDatabase();
       final String username = "dhiabsdl94@gmail.com";
		final String password = "D58059894";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("dhiabsdl94@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("ameny.ferchchichi@gmail.com"));
			message.setSubject("evenement modifier ");
			message.setText("L'evenement est modifier" );

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
        if(isvalidDate(p.getDateEvenement())) System.out.println("Oki");
         showAlert(Alert.AlertType.INFORMATION, "Form information !", "Even updated successfully!");
         Stage stage = new Stage();
          ((Node)(event.getSource())).getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("G_event.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("msgTitle");
             alert.setContentText("msgContent");
             alert.showAndWait();
        */
       
}
        
        
        
       
        
    
    }
private boolean validationChampsAjout() {
        boolean res = true ; 
       
       
        
           LocalDate localDate = LocalDate.now();
       //   dateajou.setValue(localDate); 
        
        if((textfieldnom.getText().compareTo("")==0))
        {

             
             msgTitle="un champ nom est vide";
             msgContent="veillez remplir le champ nom  ";
             
            res = false ;
        } 
        
           
      /*  if((textDat.getValue()==null))
        {

             
             msgTitle="un champ date est vide";
             msgContent="veillez remplir le champ date  ";
             
            res = false ;
        } 
        
        */
        
        if((textfielfdesc.getText().compareTo("")==0))
        {
              msgTitle="un champ descrption est vide";
             msgContent="veillez remplir le champ description  ";
             
            res = false ;
        } 
        
        if((textfieldduree.getValue()==null))
        {
              msgTitle="un champ duree est vide";
             msgContent="veillez remplir le champ duree  ";
             
            res = false ;
        } 
        
         if((textfieldimage.getText().compareTo("")==0))
        {
              msgTitle="un champ image est vide";
             msgContent="veillez remplir le champ image  ";
             
            res = false ;
        } 
         
          if((textfieldtype.getValue()==null))
        {
              msgTitle="un champ Type est vide";
             msgContent="veillez remplir le champ Type  ";
             
            res = false ;
        } 
          
           if((textfieldplace.getText().compareTo("")==0))
        {
              msgTitle="un champ nombre de place est vide";
             msgContent="veillez remplir le champ nombre de place   ";
             
            res = false ;
        } 
          if((textfieldprix.getText().compareTo("")==0))
        {
              msgTitle="un champ duree est vide";
             msgContent="veillez remplir le champ le prix  ";
             
            res = false ;
        } 
          
        
            
           if((dateajou.getValue().isBefore(LocalDate.now())))
        {

             
             msgTitle="un champ date invalide";
             msgContent="veillez entrer une date valide  ";
             
            res = false ;
        } 

   
        System.out.println("res:"+msgContent);
       /* 
        if(res == false ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle(msgTitle);
             alert.setContentText(msgContent);
             alert.showAndWait();
       }
        */
        
        return res ; 
}


private boolean validationChampsModifier() {
        boolean res = true ; 
       
        
        
           LocalDate localDate = LocalDate.now();
       //   dateajou.setValue(localDate); 
        
        if((textnom.getText().compareTo("")==0))
        {

             
             msgTitle="un champ nom est vide";
             msgContent="veillez remplir le champ nom  ";
             
            res = false ;
        } 
        
           
      /*  if((textDat.getValue()==null))
        {

             
             msgTitle="un champ date est vide";
             msgContent="veillez remplir le champ date  ";
             
            res = false ;
        } 
        
        */
        
        if((textdesc.getText().compareTo("")==0))
        {
              msgTitle="un champ descrption est vide";
             msgContent="veillez remplir le champ description  ";
             
            res = false ;
        } 
        
        if((textduree.getValue()==null))
        {
              msgTitle="un champ duree est vide";
             msgContent="veillez remplir le champ duree  ";
             
            res = false ;
        } 
        
         if((textImage.getText().compareTo("")==0))
        {
              msgTitle="un champ image est vide";
             msgContent="veillez remplir le champ image  ";
             
            res = false ;
        } 
         
          if((texttype.getValue()==null))
        {
              msgTitle="un champ Type est vide";
             msgContent="veillez remplir le champ Type  ";
             
            res = false ;
        } 
          
           if((textnbrplaces.getText().compareTo("")==0))
        {
              msgTitle="un champ duree est vide";
             msgContent="veillez remplir le champ nombre de place  ";
             
            res = false ;
        } 
          if((textprix.getText().compareTo("")==0))
        {
              msgTitle="un champ duree est vide";
             msgContent="veillez remplir le champ prix  ";
             
            res = false ;
        } 
          
         
            
           if((textDat.getValue().isBefore(LocalDate.now())))
        {

             
             msgTitle="un champ date invalide";
             msgContent="veillez entrer une date valide  ";
             
            res = false ;
        } 

   
        System.out.println("res:"+msgContent);
        
        return res ; 
}

   

    @FXML
    private void chooserfileaction(ActionEvent event) throws FileNotFoundException, IOException {
        
    
        File dest=new File("C:\\xampp\\htdocs\\techeventsImg");
        fc.setInitialDirectory(new File("C:\\"));
        selectedFile = fc.showOpenDialog(null);
       if(selectedFile!=null){
           //copier les image 
        FileUtils.copyFileToDirectory(selectedFile, dest);
        File newFile = new File("C:\\xampp\\htdocs\\techeventsImg\\"+selectedFile.getName());
        FileInputStream input = new FileInputStream(newFile);
        textfieldimage.setText(selectedFile.getName());
        System.out.println("textfieldmage:"+textfieldimage.getText());
         File file = new File("C:\\xampp\\htdocs\\techeventsImg\\"+selectedFile.getName());
        Image image = new Image(file.toURI().toString());
        Image image2 = new Image(file.toURI().toString());
        imgView.setImage(image2);
        lblimage.setText("image séléctionné avec succées");
        lblimage.setStyle("-fx-text-fill: green;");
        
    }


}
    //fonction de bloc de clavier
    public EventHandler<KeyEvent> letter_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            JFXTextField txt_TextField = (JFXTextField) e.getSource();                
            
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}
    
    
    
    public EventHandler<KeyEvent> numeric_Validation() {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if(e.getCharacter().matches("[0-9]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
}  

    @FXML
    private void chooserfileaction1(ActionEvent event) throws IOException {
          File dest=new File("C:\\xampp\\htdocs\\techeventsImg");
        fc1.setInitialDirectory(new File("C:\\"));
        selectedFile = fc1.showOpenDialog(null);
       if(selectedFile!=null){
           //copier les image 
        FileUtils.copyFileToDirectory(selectedFile, dest);
        File newFile = new File("C:\\xampp\\htdocs\\techeventsImg\\"+selectedFile.getName());
        FileInputStream input = new FileInputStream(newFile);
        textfieldimage.setText(selectedFile.getName());
        System.out.println("textfieldmage:"+textfieldimage.getText());
         File file = new File("C:\\xampp\\htdocs\\techeventsImg\\"+selectedFile.getName());
        Image image = new Image(file.toURI().toString());
        Image image2 = new Image(file.toURI().toString());
        img.setImage(image2);
        lilimage1.setText("image séléctionné avec succées");
        lilimage1.setStyle("-fx-text-fill: green;");
    }
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
}
    
    
    
    
   
        
       