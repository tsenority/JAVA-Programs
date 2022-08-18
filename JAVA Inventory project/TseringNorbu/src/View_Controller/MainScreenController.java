/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.application.Platform;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainScreenController implements Initializable {

    @FXML
    private TableView<Part> table;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> InventoryLevel;

    @FXML
    private TableColumn<Part, Double> UnitPrice;

    @FXML
    private TableView<Product> table2;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> InventoryLevel2;

    @FXML
    private TableColumn<Product, Double> UnitPrice2;

    @FXML
    private TextField PartSearch;
    @FXML
    private TextField ProductSearch;

    @FXML
    private Label PartInventory;

    @FXML
    private Label Parts;

    @FXML
    private Button SearchParts;

    @FXML
    private Button AddParts;

    @FXML
    private Button ModifyParts;

    @FXML
    private Button DeleteParts;

    @FXML
    private Button SearchProducts;

    @FXML
    private Button AddProducts;

    @FXML
    private Button ModifyProducts;

    @FXML
    private Button DeleteProducts;

    @FXML
    private Button ExitMainScreen;

    @FXML
    public void AddPartsHandler(ActionEvent event) throws IOException {
        Parent InHouseParent = FXMLLoader.load(getClass().getResource("AddInHouse.fxml"));
        Scene InHouseScene = new Scene(InHouseParent);
        //This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(InHouseScene);
        window.show();
    }

    @FXML
    public void AddProductsHandler(ActionEvent event) throws IOException {

        Parent ProductParent = FXMLLoader.load(getClass().getResource("ProductAdd.fxml"));
        Scene ProductScene = new Scene(ProductParent);
        //This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ProductScene);
        window.show();
    }

    @FXML

    void SearchPartsHandler(ActionEvent event) {
        String searchItem = PartSearch.getText();
        if (searchItem != null && searchItem.length() > 0) {

            ObservableList<Part> FoundParts = FXCollections.observableArrayList();
            for (Part p : Inventory.allParts) {
                if (p.getName().equals(searchItem)) {
                    FoundParts.add(p);
                }

            }

            table.setItems(FoundParts);
        } else {
            table.setItems(Inventory.allParts);
        }

    }

    @FXML
    void DeletePartsHandler(ActionEvent event) {
        Part selectedPart = table.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            //Confirm Delete
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete...");
            alert.setHeaderText("Deleting...");
            alert.setContentText("Are you sure want to  delete " + selectedPart.getName() + " now ?");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> Inventory.allParts.remove(selectedPart));

            //update Parts table
            table.setItems(Inventory.allParts);

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
    void ExitMainScreenHandler(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void ModifyPartsHandler(ActionEvent event) throws IOException {
        Inventory.selectedPart = table.getSelectionModel().getSelectedItem();
        Parent ModifyPartsParent;
        if (Inventory.selectedPart instanceof InhousePart) {
            ModifyPartsParent = FXMLLoader.load(getClass().getResource("ModifyPartIn.fxml"));

        } else {
            ModifyPartsParent = FXMLLoader.load(getClass().getResource("ModifyPartOutsrc.fxml"));
        }

        Scene ModifyPartScene = new Scene(ModifyPartsParent);
        //This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ModifyPartScene);
        window.show();

    }

    @FXML
    void ModifyProductsHandler(ActionEvent event) throws IOException {
        Inventory.selectedProduct = table2.getSelectionModel().getSelectedItem();

        Parent ProductParent = FXMLLoader.load(getClass().getResource("ProductModify.fxml"));
        Scene ProductScene = new Scene(ProductParent);
        //This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ProductScene);
        window.show();

    }

    @FXML
    void SearchProductsHandler(ActionEvent event) {
        String searchItem = ProductSearch.getText();
        if (searchItem != null && searchItem.length() > 0) {

            ObservableList<Product> MatchedProducts = FXCollections.observableArrayList();
            for (Product p : Inventory.products) {
                if (p.getName().equals(searchItem)) {
                    MatchedProducts.add(p);
                }

            }

            table2.setItems(MatchedProducts);
        } else {
            table2.setItems(Inventory.products);
        }

    }

    @FXML
    void DeleteProductsHandler(ActionEvent event) {

        Product selectedProduct = table2.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            if (selectedProduct.getAssociatedParts().size() < 1) {

                //Confirm Delete
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Delete...");
                alert.setHeaderText("Deleting...");
                alert.setContentText("Are you sure want to  delete " + selectedProduct.getName() + " now ?");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> Inventory.products.remove(selectedProduct));

                //update Parts table
                table2.setItems(Inventory.products);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Product has parts");
                alert.setHeaderText("Product with parts can't be deleted");
                alert.setContentText("Product with parts can't be deleted");

                alert.showAndWait();

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a Product from the table.");

            alert.showAndWait();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //PARTS
        PartID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        PartName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        table.setItems(Inventory.allParts);

        //PRODUCTS- Needs a Review
        ProductID.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        ProductName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        InventoryLevel2.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        UnitPrice2.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        table2.setItems(Inventory.products);

    }

    public static void main(String[] args) {
        launch(args);
        // starts the FX toolkit, instantiates this class, 
        // and calls start(...) on the FX Application thread:
    }

}
