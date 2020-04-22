package co.mz.osoma.editor.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jsoup.Jsoup;


public abstract class Question implements NodeObject{

    protected StringProperty question = new SimpleStringProperty();
    protected StringProperty explanation = new SimpleStringProperty();



    public Question() {
    }

    public Question(String question, String explanation) {
        this.question = new SimpleStringProperty(question);
        this.explanation = new SimpleStringProperty(explanation);
    }

    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getExplanation() {
        return explanation.get();
    }

    public StringProperty explanationProperty() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation.set(explanation);
    }

    @Override
    public NodeObject getItem() {
        return this;
    }

    @Override
    public String toString() {
        return "Question";
    }


}
