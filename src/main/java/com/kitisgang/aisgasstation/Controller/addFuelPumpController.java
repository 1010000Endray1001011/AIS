package com.kitisgang.aisgasstation.Controller;

import com.kitisgang.aisgasstation.dbClass.fuelPump;
import com.kitisgang.aisgasstation.dbHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class addFuelPumpController {
    @FXML
    private TextField fuelIdField;
    @FXML
    private CheckBox statusCheckBox;
    @FXML
    private TextField quantityField;



    private mainMenuController mainController;
    private dbHandler db;

    public void setMainController(mainMenuController controller) {
        this.mainController = controller;
    }

    @FXML
    public void saveHandle() throws SQLException, IOException {
        int fuelId = Integer.parseInt(fuelIdField.getText());
        boolean status = statusCheckBox.isSelected();
        double quantity = Double.parseDouble(quantityField.getText());

        fuelPump newPump = new fuelPump(1,fuelId,"",status,quantity);
        db.addFuelPump(newPump);
        mainController.showFuelPump();
        closeWindow();
    }
    @FXML
    private void handleCancel() {
        mainController.showFuelPump();
        closeWindow();
    }
    private void closeWindow() {
        Stage stage = (Stage) fuelIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        db = new dbHandler();
    }

}
