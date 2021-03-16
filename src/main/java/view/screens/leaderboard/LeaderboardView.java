package main.java.view.screens.leaderboard;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class LeaderboardView extends GridPane {
    // private Node attributes (controls)

    private final int numColums = 5;
    private final int numRows = 9;

    private Text title;
    private Label p1;
    private Label p2;
    private Label p3;
    private Label p4;
    private Label p5;
    private Button exit;

    public LeaderboardView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        title = new Text("Leaderboard");
        p1 = new Label("1. Player1-xxxx");
        p2 = new Label("2. Player2-xxxx");
        p3 = new Label("3. Player3-xxxx");
        p4 = new Label("4. Player4-xxxx");
        p5 = new Label("5. Player5-xxxx");
        exit = new Button("Back");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …

        this.setPadding(new Insets(10));

        for (int i = 0; i < numColums; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numColums);
            this.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);
        }

        // TODO: Make the window the same size as the main window

        this.setHgap(8);
        this.setVgap(8);

        title.textAlignmentProperty();
        this.add(title, 1, 1, 3, 1 );
        this.setHalignment(title, HPos.CENTER);
        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-font-size: 33;");

        addPlaceHolder(p1, 2);
        addPlaceHolder(p2, 3);
        addPlaceHolder(p3, 4);
        addPlaceHolder(p4, 5);
        addPlaceHolder(p5, 6);

        addButton(exit, 1, 7, 3, 1);
    }

    private void addButton(Button button, int i1, int i2, int i3, int i4){
        this.add(button, i1, i2, i3, i4 );
        button.setPrefHeight(25);
        button.setPrefWidth(200);
        button.setAlignment(Pos.CENTER);
        this.setHalignment(button, HPos.CENTER);
        button.setStyle("-fx-font-weight: BOLD");
    }

    private void addPlaceHolder(Label label, int i1){
        this.add(label, 1, i1, 3, 1);
        label.setAlignment(Pos.CENTER);
        this.setHalignment(label, HPos.CENTER);
        label.setStyle("-fx-font-size: 17;");
    }

    // package-private Getters
    // for controls used by Presenter
    public Button getExit() {
        return exit;
    }
}


