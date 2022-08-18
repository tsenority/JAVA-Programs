/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

/**
 * FXML Controller class
 *
 * @author tsering
 */
public class ProductModifyController implements Initializable {

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
    private TextField ID5;
    @FXML
    private TextField Name5;
    @FXML
    private TextField Inv5;
    @FXML
    private TextField Price5;
    @FXML
    private TextField Max5;
    @FXML
    private Label Min5;
    @FXML
    private TextField Min2;
    @FXML
    private Button Search2;
    @FXML
    private TextField Search3;
    @FXML
  
    private TableView<Part> tableview5;

    @FXML
    private TableColumn<Part, Integer> PartID5;

    @FXML
    private TableColumn<Part, String> PartName5;

    @FXML
    private TableColumn<Part, Integer> InventoryLevel5;

    @FXML
    private TableColumn<Part, Double> UnitPrice5;
    @FXML
    private Button AddProduct;
   
     @FXML
    private TableView<Part> tableview6;

    @FXML
    private TableColumn<Part, Integer> PartID6;

    @FXML
    private TableColumn<Part, String> PartName6;

    @FXML
    private TableColumn<Part, Integer> InventoryLevel6;

    @FXML
    private TableColumn<Part, Double> UnitPrice6;
    @FXML
    private Button DeleteProduct;
    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    String msg = "";
    
  
    @FXML
    public ObservableList<Part> M1=Inventory.allParts;
    @FXML
    public ObservableList<Part> M2=FXCollections.observableArrayList();

    @FXML
    private void SearchButtonHandler(ActionEvent event){
        String searchItem=Search3.getText();
        System.out.println(searchItem);
        if(searchItem != null && searchItem.length() >0){
            for(Part p : M1) {
             if (p.getName().equals(searchItem)) {
              System.out.println(p.getName()+ " " + searchItem);  
            M2 = (ObservableList<Part>) tableview6.getItems();
              M2.add(p);
              
           // tableview6.setItems(M2);
             } 
        }
    }
    }

    @FXML
    private void AddProductHandler(ActionEvent event) {
         Part P =  tableview5.getSelectionModel().getSelectedItem();
      M2.add(P);
      tableview6.setItems(M2);
    }

    @FXML
    private void DeleteProductHandler(ActionEvent event) {
         Part selectedPart = tableview6.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
        //Confirm Delete
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete...");
        alert.setHeaderText("Deleting...");
        alert.setContentText("Are you sure want to  delete " + selectedPart.getName()+" now ?");        
        alert.showAndWait();
        
        M2.remove(selectedPart);
            
                   // .filter(response -> response == ButtonType.OK)
                   // .ifPresent(response -> Inventory.allParts.remove(selectedPart));

           
        //update Parts table
       tableview6.setItems( M2);
        
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
    private void SaveProductHandler(ActionEvent event) throws IOException {
        if(isValid()){
        int Product1 = Integer.parseInt(ID5.getText());
         int Product2 = Integer.parseInt(Inv5.getText());
         Inventory.selectedProduct.setinStock(Product2);
         int Product4 = Integer.parseInt(Max5.getText());
         Inventory.selectedProduct.setMax(Product4);
         int Product5 = Integer.parseInt(Min2.getText());
         Inventory.selectedProduct.setMin(Product5);
        double Product6 = Double.parseDouble(Price5.getText());
        Inventory.selectedProduct.setPrice(Product6);
        String Product7 = Name5.getText();
        Inventory.selectedProduct.setName(Product7);
        Inventory.selectedProduct.setAssociatedParts(tableview6.getItems());
    
        
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
    private void CancelProductHandler(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Product Textfields
        ID5.setText(Inventory.selectedProduct.getProductID()+"");
        Inv5.setText(Inventory.selectedProduct.getinStock()+"");
        Min2.setText(Inventory.selectedProduct.getMin()+"");
        Max5.setText(Inventory.selectedProduct.getMax()+"");
        Name5.setText(Inventory.selectedProduct.getName()+"");
        Price5.setText(Inventory.selectedProduct.getPrice()+"");
        
        //Part Columns
        PartID5.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartName5.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel5.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice5.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableview5.setItems(Inventory.allParts);
        
        
        PartID6.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartName6.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel6.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice6.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableview6.setItems(Inventory.selectedProduct.getAssociatedParts());
        
    }
     public boolean isValid(){
        boolean valid = true;
        int Product2 = 0;
        if(Inv5.getText().length()==0){
            msg+= "Product must have Inventory Level \n";
            valid = false;
            }
        else{
             Product2 = Integer.parseInt(Inv5.getText());
        }
         int Product4 = Integer.parseInt(Max5.getText());
         int Product5 = Integer.parseInt(Min2.getText());
         double Product6 =0.0;
         try{
             Product6 = Double.parseDouble(Price5.getText());
         }
         catch(NumberFormatException e){
             
             msg+= "Product must have a price \n";
             valid = false;
         }
         
         
     
         String Product7 = "";
         if (Name5.getText().isEmpty()){
             msg+= "Product must have a name \n";
             valid = false;
         }
         else{
        Product7 = Name5.getText();
         }
         
        if(Product4<Product5){
            msg+= "Maxmimum value must be greater than minimum \n";
            valid = false;
        }
        if(Product2<Product5|| Product2>Product4){
            msg+= "Inventory must be between min and max \n";
            valid = false;
        }
        if(tableview6.getItems().size()== 0){
            msg+= "Product must have atleast one part \n";
            valid = false;
          }
        if(Inv5.getText()== ""){
            msg+= "Product must have Inventory Level \n";
            valid = false;
            }
        
        
        return valid;
    }
}
