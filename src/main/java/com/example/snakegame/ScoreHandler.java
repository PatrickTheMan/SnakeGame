package com.example.snakegame;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreHandler {

    //region [Class Variables]

    private static int score=0;
    private static ArrayList<ArrayList<String>> leaderboard;

    //endregion

    //region [Getters & Setters]

    public static void setScore(int snakeScore){
        score = snakeScore;
    }

    public static int getScore(){
        return score;
    }

    //endregion

    //region [Try for file Method]

    private static void tryForFile(){

        try {
            File savefile = new File("src/main/resources/saveFile.txt");

            if (savefile.createNewFile()){
                System.out.println("Created new saveFile: "+savefile.getName());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //endregion

    //region [Data Methods]

    public static void getData(){

        // Try for file and create one if it doesn't find it
        tryForFile();

        // Initiate leaderboard or reset if already active
        leaderboard = new ArrayList<>();

        try {

            File savefile = new File("src/main/resources/saveFile.txt");
            Scanner scanner = new Scanner(savefile);

            while (scanner.hasNextLine()){

                ArrayList<String> arrayList = new ArrayList<>();

                String dataName = scanner.nextLine();
                String dataScore = scanner.nextLine();

                arrayList.add(dataName);
                arrayList.add(dataScore);

                leaderboard.add(arrayList);
            }

            scanner.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void clearData(){

        // Try for file and create one if it doesn't find it
        tryForFile();

        // Initiate leaderboard or reset if already active
        leaderboard = new ArrayList<>();

        try {

            FileWriter writer = new FileWriter("src/main/resources/saveFile.txt");

            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //endregion

    //region [Score Methods]

    public static void saveScore(String username){

        // Try for file and create one if it doesn't find it
        tryForFile();

        try {

            File savefile = new File("src/main/resources/saveFile.txt");
            Scanner scanner = new Scanner(savefile);
            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }

            FileWriter writer = new FileWriter("src/main/resources/saveFile.txt");

            for (String s: lines){
                writer.write(s+"\n");
            }

            writer.write(username+"\n");
            writer.write(score+"\n");

            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void removeScore(String username, int score){

        // Try for file and create one if it doesn't find it
        tryForFile();

        try {

            boolean foundScore = false;

            File savefile = new File("src/main/resources/saveFile.txt");
            Scanner scanner = new Scanner(savefile);
            ArrayList<String> lines = new ArrayList<>();

            while (scanner.hasNextLine()){

                String scan = scanner.nextLine();

                if (scan.equals(username) && !foundScore){

                    System.out.println(scan);

                    lines.add(scan);

                    scan = scanner.nextLine();

                    if (scan.equals(""+score)){

                        System.out.println(scan);

                        lines.remove(lines.size()-1);

                        foundScore=true;

                    } else {
                        lines.add(scan);
                    }
                } else {
                    lines.add(scan);
                }
            }

            FileWriter writer = new FileWriter("src/main/resources/saveFile.txt");

            for (String s: lines){
                writer.write(s+"\n");
            }

            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //endregion

    //region [getLeaderboard Method]

    public static TableView getLeaderboard(){

        TableView tableView = new TableView<String>();

        TableColumn<Score, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Score, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.getColumns().addAll(usernameCol,scoreCol);

        for (ArrayList<String> arrayList : leaderboard){

            tableView.getItems().addAll(new Score(arrayList.get(0),Integer.parseInt(arrayList.get(1))));

        }

        scoreCol.setSortType(TableColumn.SortType.DESCENDING);

        tableView.getSortOrder().addAll(scoreCol);

        tableView.setLayoutX(60);
        tableView.setLayoutY(60);
        tableView.setPrefSize(300,200);

        usernameCol.setMinWidth(230);
        scoreCol.setMinWidth(50);

        return tableView;
    }

    //endregion

}
