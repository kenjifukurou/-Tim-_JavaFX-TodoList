package com.kenji.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditController {

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsField;

    @FXML
    private DatePicker deadLineField;


    public void processEdit() {
        String shortDescription = shortDescriptionField.getText().trim();

        String details = detailsField.getText().trim();

        LocalDate deadLine = deadLineField.getValue();



    }
}
