package com.kenji.todolist;

import com.kenji.todolist.datamodel.TodoData;
import com.kenji.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsDescription;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        System.out.println("initialized");
//        TodoItem item1 = new TodoItem("title a", "do this do that do x do y 1", LocalDate.of(2020, 1, 26));
//        TodoItem item2 = new TodoItem("title b", "do this do that do x do y 2", LocalDate.of(2020, 1, 27));
//        TodoItem item3 = new TodoItem("title c", "do this do that do x do y 3", LocalDate.of(2020, 1, 28));
//        TodoItem item4 = new TodoItem("title d", "do this do that do x do y 4", LocalDate.of(2020, 1, 29));
//        TodoItem item5 = new TodoItem("title e", "do this do that do x do y 5", LocalDate.of(2020, 1, 30));
//        TodoItem item6 = new TodoItem("title f", "do this do that do x do y 6", LocalDate.of(2020, 1, 31));
//
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//        todoItems.add(item6);
////
//        TodoData.getInstance().setTodoItems(todoItems);

//        detect changes in scene
        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                itemDetailsDescription.setText(item.getDetails());

                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                deadLineLabel.setText(df.format(item.getDeadline()));
//                deadLineLabel.setText(item.getDeadline().toString());
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());

        System.out.println(todoListView.getItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));

        try {
//            Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
            Parent root = fxmlLoader.load();
            dialog.getDialogPane().setContent(root);
            dialog.setTitle("Input new Todo List");

        } catch (IOException dialogError) {
            System.out.println("cannot load dialog!");
            dialogError.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("user pressed OK");
        } else {
            System.out.println("user pressed CANCEL");
        }
    }

    @FXML
    public void handleClickEvent() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        System.out.println("selected item is: " + item);
        itemDetailsDescription.setText(item.getDetails());

//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due date: ");
//        sb.append(item.getDeadline());
//        itemDetailsDescription.setText(sb.toString());

        deadLineLabel.setText(item.getDeadline().toString());

    }

    @FXML
    public void setTextBold() {
        System.out.println("set text to bold clicked");
        double n = itemDetailsDescription.getFont().getSize();
        boolean isBold = false;
        if (n <= 15) {
            n = 20;
        } else {
            n = 12;
        }
        Font boldArial = new Font("Arial", n);
        itemDetailsDescription.setFont(boldArial);
    }
}
