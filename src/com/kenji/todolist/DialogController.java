package com.kenji.todolist;

import com.kenji.todolist.datamodel.TodoData;
import com.kenji.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsField;

    @FXML
    private DatePicker deadLineField;

    public TodoItem processResult() {
        String shortDescription = shortDescriptionField.getText();
        String shortDescriptionTrim = shortDescription.trim();
        System.out.println("entered: "+ shortDescription);

        String details = detailsField.getText().trim();
        System.out.println("entered: " + details);

        LocalDate deadLine = deadLineField.getValue();
        System.out.println("entered: " + deadLine.toString());

        TodoItem newItem = new TodoItem(shortDescriptionTrim, details, deadLine);
        TodoData.getInstance().addTodoItem(newItem);
        System.out.println("entered data has stored to TodoData");

        return newItem;
    }

}
