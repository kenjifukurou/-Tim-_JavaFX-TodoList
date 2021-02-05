package com.kenji.todolist.datamodel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {

    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";

    private List<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    //getter
    public static TodoData getInstance() {
        return instance;
    }

    //private constructor = singleton
    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public void loadTodoItems() throws IOException {
        System.out.println("convert list to fxlist...");
        todoItems = FXCollections.observableArrayList();

        System.out.println("get path...");
        Path path = Paths.get(filename);

        System.out.println("buffering found path: file: " + filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            System.out.println("try fuck me");
            while((input = br.readLine()) != null) {
                System.out.println("input read line found");

                String[] stringPieces = input.split("\t");
                System.out.println("delete tab in string line...");

                System.out.println("splitting text into pieces...");
                String shortDescription = stringPieces[0];
                String details = stringPieces[1];
                String deadline = stringPieces[2];

                System.out.println("converting date...");
                LocalDate date = LocalDate.parse(deadline, formatter);

                System.out.println("assigning info into TodoItem...");
                TodoItem todoItem = new TodoItem(shortDescription, details, date);

                System.out.println("add TodoItem into todoItem list...");
                todoItems.add(todoItem);
            }
        } finally {
            if (br != null) {
                System.out.println("file successful load, fuck off now!");
                br.close();
            }
        }
    }

    public void saveTodoItems() throws IOException {

        System.out.println("saving: get path...");
        Path path = Paths.get(filename);
        System.out.println("write path to bufferedwriter...");
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {
            System.out.println("create iterator on todoitem");
            Iterator<TodoItem> iter = todoItems.iterator();

            System.out.println("if got next item, keep iterate");
            while(iter.hasNext()) {
                TodoItem item = iter.next();
                System.out.println("save text strings");
                bw.write(String.format("%s\t%s\t%s", item.getShortDescription(), item.getDetails(), item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            System.out.println("file has loaded");
            System.out.println("iterate finish, fuck off");
            if (bw != null) {
                bw.close();
            }
        }
    }
}
