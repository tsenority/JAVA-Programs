
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
   public static ObservableList<Part> allParts= FXCollections.observableArrayList();
    
    
    public static ObservableList<Product> products= FXCollections.observableArrayList();

      /*  products.add(new Product("Samsung"));
        products.add(new Product("Apple"));
        products.add(new Product("Nokia"));
        products.add(new Product("Sony"));
        products.add(new Product("LG"));
        products.add(new Product("HTC"));
        products.add(new Product("Motorola"));
*/
      public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getProducts() {
        return products;
    }
    public static Part selectedPart;
   public static Product selectedProduct;
}
