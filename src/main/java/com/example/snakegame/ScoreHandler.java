package com.example.snakegame;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <Strong>This is for handling the different aspects related to score</Strong>
 */
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

    /**
     * <Strong>This is used for trying to find the saveFile or creation a new one</Strong>
     */
    private static void tryForFile(){

        try {
            // Trys to find the save file
            File savefile = new File("src/main/resources/saveFile.txt");

            // Try to create a new file, and prints out if it did create a new one
            if (savefile.createNewFile()){
                System.out.println("Created new saveFile: "+savefile.getName());
            }

        } catch (Exception e){
            System.out.println("Error: tryForFile");
        }

    }

    //endregion

    //region [Data Methods]

    /**
     * <Strong>This is for updating the data in the program, by getting the newest data from the saveFile</Strong>
     */
    public static void getData(){

        // Try for file and create one if it doesn't find it
        tryForFile();

        // Initiate leaderboard or reset if already active
        leaderboard = new ArrayList<>();

        try {

            // The saveFile to look in
            File savefile = new File("src/main/resources/saveFile.txt");
            Scanner scanner = new Scanner(savefile);

            // Gets the different lines and adds them to the leaderboard arraylist
            while (scanner.hasNextLine()){

                ArrayList<String> arrayList = new ArrayList<>();

                String dataName = scanner.nextLine();
                String dataScore = scanner.nextLine();

                arrayList.add(dataName);
                arrayList.add(dataScore);

                leaderboard.add(arrayList);
            }

            // Closes the scanner
            scanner.close();

        } catch (Exception e){
            System.out.println("Error: getData");
        }
    }

    /**
     * <Strong>This is for clearing the data in the saveFile</Strong>
     */
    public static void clearData(){

        // Try for file and create one if it doesn't find it
        tryForFile();

        // Initiate leaderboard or reset if already active
        leaderboard = new ArrayList<>();

        try {

            // Creates a new fileWriter with the saveFile as a output thereby overwriteing it
            FileWriter writer = new FileWriter("src/main/resources/saveFile.txt");

            // The writer gets closed without writing anything, thereby clearing the file
            writer.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //endregion

    //region [Score Methods]

    /**
     * <Strong>This is for saving new scores to the saveFile</Strong>
     * @param username is the name of the user, who just played
     */
    public static void saveScore(String username){

        // Try for file and create one if it doesn't find it
        tryForFile();

        try {

            // The saveFile
            File savefile = new File("src/main/resources/saveFile.txt");
            Scanner scanner = new Scanner(savefile);
            ArrayList<String> lines = new ArrayList<>();

            // The scanner adds all savelines to the lines array
            while (scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }

            // A writer gets initiated with the saveFile as output, thereby overwriting it
            FileWriter writer = new FileWriter("src/main/resources/saveFile.txt");

            // The savelines in lines gets writen into the savFile
            for (String s: lines){
                writer.write(s+"\n");
            }

            // The new score gets writen into the saveFile
            writer.write(username+"\n");
            writer.write(score+"\n");

            // The writer gets closed
            writer.close();

        } catch (Exception e){
            System.out.println("Error: saveScore");
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

    /**
     * <Strong>This is for getting the leaderboard</Strong>
     * @return a tableView with the scores from the leaderboard arraylist data
     */
    public static TableView getLeaderboard(){

        // The tableView
        TableView tableView = new TableView<String>();

        // The usernameCol
        TableColumn<Score, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        // The scoreCol
        TableColumn<Score, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        // The collums gets added
        tableView.getColumns().addAll(usernameCol,scoreCol);

        // The tableView gets populated with the data in the leaderboard
        for (ArrayList<String> arrayList : leaderboard){
            // The leaderboard contains arraylists with the username and the score in strings
            tableView.getItems().addAll(new Score(arrayList.get(0),Integer.parseInt(arrayList.get(1))));
        }

        // The scoreCol gets a rule, that it should start as descending
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);

        // The tableView uses the rule from before
        tableView.getSortOrder().addAll(scoreCol);

        // The tableViews layout and the col sizes gets set
        tableView.setLayoutX(60);
        tableView.setLayoutY(60);
        tableView.setPrefSize(300,200);

        usernameCol.setMinWidth(230);
        scoreCol.setMinWidth(50);

        // The finished leaderboard (tableView) gets returned
        return tableView;
    }

    //endregion

}
