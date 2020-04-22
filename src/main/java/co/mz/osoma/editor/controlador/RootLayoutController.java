package co.mz.osoma.editor.controlador;

import co.mz.osoma.editor.modelo.Exam;
import co.mz.osoma.editor.modelo.QuestionMultiChoice;
import co.mz.osoma.editor.modelo.RootObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(((QuestionMultiChoice)((RootObject)this.mainGUIController.rootNode.getValue()).getExams().get(0).getQuestions().get(0)).getChoices().get(0));
            System.out.println(str);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("teste");
            alert.show();
        }catch (JsonProcessingException ex){
            System.out.println(ex.getMessage());
        }

    }
}
