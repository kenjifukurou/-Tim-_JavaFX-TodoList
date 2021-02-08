package com.kenji.todolist;

import com.kenji.todolist.datamodel.TodoData;
import com.kenji.todolist.datamodel.TodoItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("To Do List");
        primaryStage.setScene(new Scene(root, 800, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void stop() throws Exception {
        System.out.println("program stop, try putting dick into cibai...");
        try {
            System.out.println("loading singleton TodoData");
            TodoData.getInstance().saveTodoItems();

        } catch(IOException ioError) {
            System.out.println(ioError.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        System.out.println("loading mother fucker todolist...");
        try {
            TodoData.getInstance().loadTodoItems();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
