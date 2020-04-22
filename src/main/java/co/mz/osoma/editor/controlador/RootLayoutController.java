package co.mz.osoma.editor.controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class RootLayoutController implements Initializable {

    MainGUIController mainGUIController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setMainGUIController(MainGUIController mainGUIController){
        this.mainGUIController = mainGUIController;
    }

    @FXML
    public void handleSave() {

        System.out.println(this.mainGUIController.rootNode);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("teste");
        alert.show();
    }
}
