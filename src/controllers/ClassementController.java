/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Classement;
import Entites.Classementf;
import Service.ServiceClassement;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
import Service.ServiceTeam;
import Service.ServiceWeek;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ClassementController implements Initializable {
ArrayList <Classementf> listclassementf= new ArrayList<Classementf>();
ArrayList <Classement> listclassement= new ArrayList<Classement>();
    @FXML
    private TableColumn<?, ?> rang;
    @FXML
    private TableColumn<Classementf, ?> logo;
    @FXML
    private TableColumn<Classementf, String> team;
    @FXML
    private TableColumn<Classementf, String> play;
    @FXML
    private TableColumn<Classementf, String> win;
    @FXML
    private TableColumn<Classementf, String> lost;
    @FXML
    private TableColumn<Classementf, String> diff;
    @FXML
    private TableColumn<Classementf, String> pts;
 ArrayList<Classement> classements = new ArrayList<Classement>(); // Create an ArrayList object
    @FXML
    private TableView<Classementf> table_classement = new TableView<Classementf>();
    @FXML
    private Label ftCompetition;
    @FXML
    private Label ftPhase;
    @FXML
    private Button btDashboard;
    @FXML
    private Button btPDF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ftCompetition.setText(ServiceCompetition.getCom().getName());
        ftPhase.setText(ServicePhase.getCom().getName());
        affichertable();
    }    
    
    public void affichertable (){
   
        int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        
        ServiceClassement sc = new ServiceClassement ();
        ServiceTeam st = new ServiceTeam();
        
        
        try {
           
            classements = (ArrayList<Classement>) sc.AfficherClassement(idc, idp);
            
            try {
            for (Classement c : classements) {
           Classementf cf = new Classementf();
           
           
           
           cf.setId(c.getId());
           cf.setName_team(st.GetTeamById(c.getId_team()).getName());
           cf.setNbr_pts_D(c.getNbr_pts_D());
           cf.setNbr_pts_G(c.getNbr_pts_G());
           cf.setNbr_pts_P(c.getNbr_pts_P());
           cf.setPts_diff(c.getPts_diff());
           cf.setNbr_pts(c.getNbr_pts());
           
           
           listclassementf.add(cf);
           sc.data1.add(cf);
                
           
           
            
                
            
        }
                
        } catch (SQLException ex) {
            Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            rang.setCellValueFactory(new PropertyValueFactory<>(""));
            logo.setCellValueFactory(new PropertyValueFactory<>("logo_team"));
            team.setCellValueFactory(new PropertyValueFactory<>("name_team"));
            play.setCellValueFactory(new PropertyValueFactory<>("nbr_pts_P"));
            win.setCellValueFactory(new PropertyValueFactory<>("nbr_pts_G"));
            lost.setCellValueFactory(new PropertyValueFactory<>("nbr_pts_D"));
            diff.setCellValueFactory(new PropertyValueFactory<>("pts_diff"));
            pts.setCellValueFactory(new PropertyValueFactory<>("nbr_pts"));
            
            
            table_classement.setItems(sc.getData1());
            //table_classement.getSortOrder().add(pts);
            
            pts.setSortType(TableColumn.SortType.DESCENDING);
            table_classement.getSortOrder().add(pts);
            table_classement.sort();
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    @FXML
    private void Back(ActionEvent event) throws IOException {
        btDashboard.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("FTBB Application");
            stage.setScene(new Scene(root1));  
            stage.show();
            
    }

//    @FXML
//    private void SavePDF(ActionEvent event) {
//        String path="";
//        JFileChooser j = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int x=j.showSaveDialog(j);
//        if (x==JFileChooser.APPROVE_OPTION){
//        path=j.getSelectedFile().getPath();
//        }
//       
//        Document doc = new DefaultStyledDocument();
//    try {
//        PdfWriter.getInstance(doc,new FileOutputStream(path+"abc.pdf"));
//        doc.open();
//           PdfTable tb1= new PdfTable(3);
//           tb1.addCell("test1");
//           tb1.addCell("test2");
//           tb1.addCell("test3");
//        for (int i = 0; i < table_classement.getRowFactory(); i++) {
//            
//        }
//        
//    } catch (FileNotFoundException ex) {
//        Logger.getLogger(ClassementController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//         
//        
//        
//        
//        
//        
//    }

    @FXML
    private void SavePDF(ActionEvent event) {
    }
}
