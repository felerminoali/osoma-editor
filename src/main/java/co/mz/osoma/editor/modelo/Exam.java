package co.mz.osoma.editor.modelo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Exam implements NodeObject{
    private StringProperty title = new SimpleStringProperty();
    private ObservableList<Question> questions = FXCollections.observableArrayList();


    public Exam() {
    }

    public Exam(String title, ObservableList<Question> questions) {
        this.title = new SimpleStringProperty(title);
        this.questions = questions;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ObservableList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Exam";
    }

    @Override
    public NodeObject getItem() {
        return this;
    }

    @Override
    public List<NodeObject> hasChild() {
        if(questions.size()==0)return null;
        List<NodeObject> nodeObjects = new ArrayList<>();
        for (NodeObject n : questions){
            nodeObjects.add(n);
        }
        return nodeObjects;
    }
}
