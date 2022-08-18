/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Product;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Part;

/**
 * FXML Controller class
 *
 * @author tsering
 */
public class ProductAddController implements Initializable {
  

     
 @FXML
    private Label AddProduct;

    @FXML
    private Label ID;

    @FXML
    private Label Name;

    @FXML
    private Label Inv;

    @FXML
    private Label Price;

    @FXML
    private Label Max;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField InvTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField MaxTextField;

    @FXML
    private Label Min;

    @FXML
    private TextField MinTextField;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField SearchTextfield;

    @FXML
    private TableView<Part> tableview3;

    @FXML
    private TableColumn<Part,Integer> PartID3;

    @FXML
    private TableColumn<Part, String> PartName3;

    @FXML
    private TableColumn<Part, Integer> InventoryLevel3;

    @FXML
    private TableColumn<Part, Double> UnitPrice3;

    @FXML
    private Button Add;

    @FXML
    private TableView<Part> tableview4;

    @FXML
    private TableColumn<Part, Integer> PartID4;

    @FXML
    private TableColumn<Part, String> PartName4;

    @FXML
    private TableColumn<Part, Integer> InventoryLevel4;

    @FXML
    private TableColumn<Part, Double> UnitPrice4;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button Cancel;
    String msg = "";
    
    @FXML
    void AddProductHandler(ActionEvent event) {
      Part P =  tableview3.getSelectionModel().getSelectedItem();
      data2.add(P);
      tableview4.setItems(data2);
        
        
        

    }

    @FXML
    void CancelHandler(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Cancelling");
        alert.setContentText("Do you want to cancel and go back to main screen?");        
        alert.showAndWait();
        
       Parent MainScreenParent =  FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
       Scene MainScreenScene = new Scene(MainScreenParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(MainScreenScene);
       window.show();

    }

    @FXML
    void DeleteHandler(ActionEvent event) {
        Part selectedPart = tableview4.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
        //Confirm Delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete...");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Are you sure want to  delete " + selectedPart.getName()+" now ?");        
        alert.showAndWait();
        
        data2.remove(selectedPart);
            
                   // .filter(response -> response == ButtonType.OK)
                   // .ifPresent(response -> Inventory.allParts.remove(selectedPart));

           
        //update Parts table
       tableview4.setItems( data2);
        
        } else {
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Part Selected");
        alert.setContentText("Please select a Part from the table.");

        alert.showAndWait();

    }
    }
   @FXML
   public void SaveHandler(ActionEvent event) throws IOException {
        if(isValid()){
         int Product2 = Integer.parseInt(InvTextField.getText());
         int Product4 = Integer.parseInt(MaxTextField.getText());
         int Product5 = Integer.parseInt(MinTextField.getText());
        double Product6 = Double.parseDouble(PriceTextField.getText());
        String Product7 = NameTextField.getText();
       
        
        Product P = new Product(0,Product7,Product6,Product2,Product5,Product4);
        P.setAssociatedParts(tableview4.getItems());
        double pricetotal = 0;
        for(Part each: P.getAssociatedParts()){
            pricetotal+= each.getPrice();
        }
        if(!(pricetotal<= P.getPrice()))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Invalid input");
        alert.setHeaderText("Invalid input");
        alert.setContentText("The sum of the prices  of Associated parts "+pricetotal+" must be less than a price of a Product");  
        
        alert.showAndWait();
        }
        else{
            
         Inventory.products.add(P);
        }
        
         Parent MainScreenParent =  FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
       Scene MainScreenScene = new Scene(MainScreenParent);
       //This line gets the stage information
       Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(MainScreenScene);
       window.show();
        }
        else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid input");
        alert.setHeaderText("Invalid input");
        alert.setContentText(msg);

        alert.showAndWait();
          msg = "";  
        }

    }
    
    @FXML
    public ObservableList<Part> data=Inventory.allParts;
    @FXML
    public ObservableList<Part> data2=FXCollections.observableArrayList();

    @FXML
    void SearchPartsHandler(ActionEvent event) {
        String searchItem=SearchTextfield.getText();
        System.out.println(searchItem);
        if(searchItem != null && searchItem.length() >0){
            for(Part p : data) {
             if (p.getName().equals(searchItem)) {
              System.out.println(p.getName()+ " " + searchItem);  
             data2 = (ObservableList<Part>) tableview4.getItems();
              data2.add(p);
              
             /* PartID4.setCellValueFactory(new PropertyValueFactory<>("PartID3"));
              PartName4.setCellValueFactory(new PropertyValueFactory<>("partName3"));
              InventoryLevel4.setCellValueFactory(new PropertyValueFactory<>("InventoryLevel3"));
              UnitPrice4.setCellValueFactory(new PropertyValueFactory<>("UnitPrice3"));*/
              
                    

             tableview4.setItems(data2);
             } 
        }
        }
    }
        

  @Override
    public void initialize(URL location, ResourceBundle resources) {
        PartID3.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartName3.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel3.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice3.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableview3.setItems(Inventory.allParts);
        
        
        PartID4.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartName4.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel4.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice4.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
      
       
        
    }   
    public boolean isValid(){
        boolean valid = true;
        int Product2 = 0;
        if(InvTextField.getText().length()== 0){
            msg+= "Product must have Inventory Level \n";
            valid = false;
            }
        else{
             Product2 = Integer.parseInt(InvTextField.getText());
        }
         int Product4 = Integer.parseInt(MaxTextField.getText());
         int Product5 = Integer.parseInt(MinTextField.getText());
         double Product6 =0.0;
         try{
             Product6 = Double.parseDouble(PriceTextField.getText());
         }
         catch(NumberFormatException e){
             
             msg+= "Product must have a price \n";
             valid = false;
         }
         
         
     
         String Product7 = "";
         if (NameTextField.getText().isEmpty()){
             msg+= "Product must have a name \n";
             valid = false;
         }
         else{
        Product7 = NameTextField.getText();
         }
         
        if(Product4<Product5){
            msg+= "Maxmimum value must be greater than minimum \n";
            valid = false;
        }
         if(Product5>Product4){
            msg+= "Minimum value must be less than maximum \n";
            valid = false;
        }
        if(Product2<Product5|| Product2>Product4){
            msg+= "Inventory must be between min and max \n";
            valid = false;
        }
        if(tableview4.getItems().size()== 0){
            msg+= "Product must have atleast one part \n";
            valid = false;
          }
        if(InvTextField.getText()== ""){
            msg+= "Product must have Inventory Level \n";
            valid = false;
            }
        
        
        return valid;
    }
}
        

    


   
 
        
       
       
    
    
    
