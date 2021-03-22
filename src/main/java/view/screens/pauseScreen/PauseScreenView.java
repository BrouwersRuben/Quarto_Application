package main.java.view.screens.pauseScreen;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class PauseScreenView extends GridPane {

    private final int numColums = 5;
    private final int numRows = 5;

    // private Node attributes (controls)
    private Text title;
//    private Label passedTime;
    private Button startGame;
    private Button mainMenu;

    public PauseScreenView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        // create and configure controls
        // button = new Button("...")
        // label = new Label("...")
        title = new Text("PauseMenu");
//        passedTime = new Label();
        startGame = new Button("Resume the Game!");
        mainMenu = new Button("Go to main menu");
    }

    private void layoutNodes() {
        // add/set … methods
        // Insets, padding, alignment, ...
        this.setWidth(425);
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

        this.setHgap(8);
        this.setVgap(8);
        this.setWidth(900);

        title.textAlignmentProperty();
        this.add(title, 1, 1, 3, 1 );
        this.setHalignment(title, HPos.CENTER);
        title.setStyle("-fx-font-size: 25;");

        addButton(mainMenu, 0, 3, 3, 1);
        addButton(startGame, 2, 3, 3, 1);
    }

    private void addButton(Button button, int i1, int i2, int i3, int i4){
        this.add(button, i1, i2, i3, i4 );
        button.setPrefHeight(25);
        button.setPrefWidth(200);
        button.setAlignment(Pos.CENTER);
        this.setHalignment(button, HPos.CENTER);
        button.setStyle("-fx-font-weight: BOLD");
    }

    // package-private Getters
    // for controls used by Presenter
    public Text getTitle() {
        return title;
    }
    public Button getStartGame() {
        return startGame;
    }
    public Button getMainMenu() {
        return mainMenu;
    }
}

