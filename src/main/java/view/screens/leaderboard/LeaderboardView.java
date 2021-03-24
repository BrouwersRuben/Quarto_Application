package main.java.view.screens.leaderboard;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class LeaderboardView extends GridPane {
    // private Node attributes (controls)
    private final int numColumns = 5;
    private final int numRows = 9;

//    private GridPane pane;
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
        // TODO: these labels won't initialise if there's not 5 entries in the table ( ARRAYOUTOFBOUNDS )
        p1 = new Label();
        p2 = new Label();
        p3 = new Label();
        p4 = new Label();
        p5 = new Label();
        exit = new Button("Back");
//        pane = new GridPane();
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, …

//        this.setCenter(pane);


        this.setPadding(new Insets(10));

        for (int i = 0; i < numColumns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numColumns);
            this.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            this.getRowConstraints().add(rowConst);
        }

        this.setHgap(8);
        this.setVgap(8);

        title.textAlignmentProperty();
        this.add(title, 1, 1, 3, 1 );
        this.setHalignment(title, HPos.CENTER);
        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-font-size: 33;");

        addLabel(p1, 2);
        addLabel(p2, 3);
        addLabel(p3, 4);
        addLabel(p4, 5);
        addLabel(p5, 6);

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

    private void addLabel(Label label, int i1){
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

    public Label getText1() {
        return p1;
    }
    public Label getText2() {
        return p2;
    }
    public Label getText3() {
        return p3;
    }
    public Label getText4() {
        return p4;
    }
    public Label getText5() {
        return p5;
    }
}



