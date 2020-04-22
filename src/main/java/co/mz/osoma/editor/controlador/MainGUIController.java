package co.mz.osoma.editor.controlador;

import co.mz.osoma.editor.MainApp;
import co.mz.osoma.editor.modelo.*;
import co.mz.osoma.editor.service.CallbackCustom;
import co.mz.osoma.editor.service.PaneFactory;
import co.mz.osoma.editor.service.TreeItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainGUIController implements Initializable {

    @FXML
    private TreeView<Object> treeCon;

    @FXML
    public BorderPane mainBoder;

    protected TreeItem<Object> rootNode;

    private MainApp mainApp;



    public TreeItem<Object> makeBranch(Object node, TreeItem<Object> parent) {
        TreeItem<Object> item = new TreeItem<>(node);
        item.setExpanded(true);
        parent.getChildren().add(item);

        return item;
    }

    public TreeItem<Object> findRootObject(TreeItem<Object> current){
        if(current.getParent().getParent() == null) return current;
        return findRootObject(current.getParent());
    }

    public TreeItem<Object> makeTree(TreeItem<Object> parent, NodeObject node) {

        if(node == null) return null;

        TreeItem<Object> item = new TreeItem<>(node);
        item.setExpanded(true);
        parent.getChildren().add(item);

        if(node.hasChild() != null){
            for (NodeObject n:node.hasChild()){
                makeTree(item, n);
            }
        }
        return item;
    }

    public MainGUIController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        populateTree();

        treeCon.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }



    private void showDetails(Object obj) {

        try {

            TreeItem<Object> item = (TreeItem<Object>) obj;

            PaneFactory paneFactory = new PaneFactory(item.getValue(),this);
            Pane page = paneFactory.create();

            TreeItemController treeItemController = paneFactory.getLoader().getController();
            treeItemController.fillForm(item.getValue());

            this.mainBoder.setCenter(page);
        } catch (IOException e) {

        }

    }

    public void populateTree() {
        try {

            ObservableList<Question> list = FXCollections.observableArrayList();
//            List<Question> list = new ArrayList<>();

            QuestionMultiChoice questionMultiChoice = new QuestionMultiChoice();

            Choice choice = new Choice();

//            choice.setDescription("ahajhdjada");
            questionMultiChoice.getChoices().add(choice);


            list.add(questionMultiChoice);
            list.add(new QuestionMultiChoice());



            TreeItem<Object> root = new TreeItem<>();
            root.setExpanded(true);

            Exam exam = new Exam("Exame de Pt",  list);

            List<Exam> exams = new ArrayList<>();

            exams.add(exam);

            RootObject rootObject = new RootObject(exams);

            rootObject.setNumber("12.0");
            rootObject.setTitle("olE");

            rootObject.setFileVersion("0.0");
//            rootObject.setPassingScore(20);
//            rootObject.setTimeLimit(2);

            rootNode = makeTree(root,rootObject);

            // create tree
            treeCon.setRoot(root);
            // defines a custom tree cell factory for the tree view
            treeCon.setCellFactory(new CallbackCustom(this));
            treeCon.setShowRoot(false);


        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText( ex.getMessage());
            alert.show();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public TreeItem<Object> getRootNode(){ return rootNode;}

    public TreeItem<Object> getSeletedItem(){
        return this.treeCon.getSelectionModel().getSelectedItem();
    }
}
