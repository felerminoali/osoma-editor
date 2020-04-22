package co.mz.osoma.editor.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Choice implements NodeObject{

    private StringProperty label = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public Choice() {
    }

    public Choice(StringProperty description) {
        this.description = description;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }


    public String getLabel() {
        return label.get();
    }

    public StringProperty labelProperty() {
        return label;
    }

    public void setLabel(String label) {
        this.label.set(label);
    }


    @Override
    public String toString() {
        return "Choice{" +
//                "description=" + description.get() + " "+
//                "label=" + label.get() +
                '}';
    }

    @JsonIgnore
    @Override
    public NodeObject getItem() {
        return this;
    }

    @Override
    public List<NodeObject> hasChild() {
        return null;
    }



}
