/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.gestion_store;



import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import store.src.Entities.Command;
import store.src.Service.ServiceCommand;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;
import utils.Passable;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CommandAdminController implements Initializable {

    @FXML
    private TableView<Command> lcommand;
    @FXML
    private TableColumn<?, ?> colcommandid;
    @FXML
    private TableColumn<?, ?> colidclient;
    @FXML
    private TableColumn<?, ?> coldatecommand;
    @FXML
    private TableColumn<?, ?> colstatus;
    @FXML
    private TableColumn<?, ?> coltotalprice;
    @FXML
    private TextField rechercher_command;
    @FXML
    private Button export;
    @FXML
    private Button back;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
       /* TableColumn t1 = new TableColumn("command_id");
        t1.setCellValueFactory(new PropertyValueFactory<Command, Integer>("command_id"));
        TableColumn t2 = new TableColumn("id_client");
        t2.setCellValueFactory(new PropertyValueFactory<Command, Integer>("id_client"));
        TableColumn t3 = new TableColumn("date_command");
        t3.setCellValueFactory(new PropertyValueFactory<Command, Date>("date_command"));
        TableColumn t4 = new TableColumn("status");
        t4.setCellValueFactory(new PropertyValueFactory<Command, Integer>("status"));
        TableColumn t5 = new TableColumn("total_price");
        t5.setCellValueFactory(new PropertyValueFactory<Command, Integer>("total_price"));
                lcommand.getColumns().addAll(t1,t2,t3,t4,t5);*/
       
       ServiceCommand sc = new ServiceCommand();
        List<Command> l = sc.showCommand();
        ObservableList<Command> list  = FXCollections.observableArrayList(l);
        colcommandid.setCellValueFactory(new PropertyValueFactory<>("command_id"));       
        colidclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));        
        coldatecommand.setCellValueFactory(new PropertyValueFactory<>("date_command"));        
        colstatus.setCellValueFactory(new PropertyValueFactory<>("status"));        
        coltotalprice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
      
        lcommand.setItems(list);
        
        
        
        /*********** RECHERCHE D'UN PRODUIT ***************************************************************************************************************************************************/
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Command> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher_command.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Command -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Command.getCommand_id()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Command.getId_client()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Command.getDate_command()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Command.getStatus()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Command.getTotal_price()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Command> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(lcommand.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		lcommand.setItems(sortedData);
    
      
             Command productselected = lcommand.getSelectionModel().getSelectedItem();

    }    

    private void refreshButton(ActionEvent event) {
        ServiceCommand sc = new ServiceCommand();
        List<Command> l = sc.showCommand();
        
        ObservableList<Command> data=FXCollections.observableArrayList(l);
        lcommand.setItems(data);
    }

    @FXML
    private void onValid(ActionEvent event) {
        Command c  = lcommand.getSelectionModel().getSelectedItem();
        ServiceCommand sc = new ServiceCommand();
       
        
        //aleeeeeeeeeeeeeeeert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS VALIDATION");
        alert.setHeaderText(null);
        alert.setContentText("COMMAND VALIDATED !");
        alert.showAndWait();
        
        sc.updateCommand(c, 1);
        ServiceClient ss = new ServiceClient();
        Client cc = ss.getA();
        sc.sendEmail(c.getCommand_id(), cc.getId());
        this.refreshButton(null);
        
        
    }

    @FXML
    private void btnback(ActionEvent event) throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/store/src/gestion_store/page1.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        
        //stage.initStyle(StageStyle.UNDECORATED);
       
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void export(ActionEvent event) throws IOException {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/store/src/gestion_store/PdfView.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(pidev.ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   }

