package com.kitisgang.aisgasstation.Controller;

import com.kitisgang.aisgasstation.dbClass.Inventory;
import com.kitisgang.aisgasstation.dbHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class optionalProductsController {
    dbHandler db = new dbHandler();

    @FXML
    ListView listViewOptionalProducts;

    @FXML
    ObservableList<Inventory> inventory = FXCollections.observableArrayList();

    @FXML
    Button okButton;

    @FXML
    void initialize() {
        new dbHandler();
        showOptionalProducts();
    }

    @FXML
    public void showOptionalProducts() {
        listViewOptionalProducts.setItems(inventory);
        listViewOptionalProducts.setCellFactory(fuelPumpListView -> new ListCell<Inventory>() {
            @Override
            protected void updateItem(Inventory inventory, boolean empty) {
                super.updateItem(inventory, empty);
                if (empty || inventory == null) {
                    setText(null);
                } else {
                    setText(String.format("Номер продукта: %d | Название продукта: %s | Количество продукции: %d",
                            inventory.getId(),
                            inventory.getName(),
                            inventory.getHow_much()));
                }
            }
        });
        db.loadInventoryInfoFromDatabase(inventory);
    }
    @FXML
    private void handleCancel() {;
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

}
