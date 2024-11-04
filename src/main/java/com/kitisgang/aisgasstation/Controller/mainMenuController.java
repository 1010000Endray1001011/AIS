package com.kitisgang.aisgasstation.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.kitisgang.aisgasstation.dbHandler;
import com.kitisgang.aisgasstation.dbClass.fuelPump;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class mainMenuController {
    dbHandler db = new dbHandler();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem ButtonChangeGasStation;

    @FXML
    private Button ButtonSignInKlient;

    @FXML
    private Button ButtonWriteOffBonusKlient;

    @FXML
    private SplitMenuButton SplitMenuProduct;

    @FXML
    private Button ButtonOnloadSupply;

    @FXML
    private Button ButtonDeleteFuelPump;

    @FXML
    private Button ButtonOrderSupply;

    @FXML
    private Button ButtomRedactSupply;

    @FXML
    private MenuItem ButtonDeleteEmpoyee;

    @FXML
    private Label LabelInventorylQuantityAI92;

    @FXML
    private MenuItem ButtonShowSales;

    @FXML
    private Label LabelOrderCost;

    @FXML
    private TextField TextFieldQuantityProduct;

    @FXML
    private Label LabelGereralQuantityAI98;

    @FXML
    private Button ButtonOrderDelete;

    @FXML
    private Label LabelGereralQuantityAI95;

    @FXML
    private Button ButtonShowAdditionalProduct;

    @FXML
    private Label LabelGereralQuantityAI92;

    @FXML
    private MenuItem ButtonDeleteManager;

    @FXML
    private Label LabelGeneralCost;

    @FXML
    private Button ButtonAddOrder;

    @FXML
    private Label LabelBonusKlient;

    @FXML
    private MenuItem ButtonRegisterManager;

    @FXML
    private ListView<?> ListViewOrders;

    @FXML
    private MenuItem ButtonCloseWorkShift;

    @FXML
    private MenuItem ButtonHelp;

    @FXML
    private Label LabelNameKlient;

    @FXML
    private Button ButtonRedactOrder;

    @FXML
    private Label LabelInventorylQuantityDP;

    @FXML
    private AnchorPane mainPlate;

    @FXML
    private MenuItem ButtonRegisterEmployee;

    @FXML
    private Label LabelNumberKlient;

    @FXML
    private Button ButtonAddFuelPump;

    @FXML
    private Button ButtomDistributeInventory;

    @FXML
    private Label LabelStatus;

    @FXML
    private Button ButtonLogInKlient;

    @FXML
    private ListView<fuelPump> ListViewProduct;

    private ObservableList<fuelPump> productList = FXCollections.observableArrayList();

    @FXML
    private ListView<?> ListViewSupply;

    @FXML
    private Button ButtonOrderComplete;

    @FXML
    private Label LabelGereralQuantityDP;

    @FXML
    public void showFuelPump() {
        ListViewProduct.setItems(productList);
        ListViewProduct.setCellFactory(fuelPumpListView -> new ListCell<fuelPump>() {
            @Override
            protected void updateItem(fuelPump fuelPump, boolean empty) {
                super.updateItem(fuelPump, empty);
                if (empty || fuelPump == null) {
                    setText(null);
                } else {
                    setText(String.format("Номер колонки: %d | Название бензина: %s | Статус исправности: %b | Объем топлива: %.2f литра(ов)",
                            fuelPump.getPump_id(),
                            fuelPump.getFuel_name(),
                            fuelPump.getStatus(),
                            fuelPump.getFuel_quantity()));
                }
            }
        });
        db.loadPumpInfoFromDatabase(productList);
    }

    @FXML
    private void deleteFuelPump() {
        fuelPump selectedFuelPump = ListViewProduct.getSelectionModel().getSelectedItem();
        if (selectedFuelPump != null) {
            try {
                db.deleteFuelPump(selectedFuelPump.getPump_id());
                ListViewProduct.getItems().remove(selectedFuelPump);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void addFuelPump() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlUrl = getClass().getResource("/com/kitisgang/aisgasstation/Add-fuelpump.fxml");
        loader.setLocation(fxmlUrl);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Добавить топливную колонку");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));

        addFuelPumpController controller = loader.getController();
        controller.setMainController(this);

        stage.show();
    }


    @FXML
    void initialize() {

        //продукция(бенз\колонки)
        showFuelPump();



    }

}
