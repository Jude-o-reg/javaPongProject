package org.example.projectpart1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.example.projectpart1.model.Shape;
import org.example.projectpart1.model.ShapeFactory;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Stack;


public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane canvasHolder;
    @FXML
    private Label player1Name;
    @FXML
    private Label player2Name;
    @FXML
    private Label player1score;
    @FXML
    private Label player2score;

    private Shape ball = ShapeFactory.createShape("circle");
    private Shape racket1 = ShapeFactory.createShape("rectangle");
    private Shape racket2 = ShapeFactory.createShape("rectangle");
    private Font font = new Font(20);

    private double ballSpeed;
    private double ballAcceleration;

    private double racketHeight;
    private double racketWidth;

    private int winCondition;

    /*@FXML
    private void drawCircle() {draw("circle");}

    @FXML
    private void drawRectangle() {draw("rectangle");}

    private void draw(String type){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0, canvas.getWidth(),canvas.getHeight());

        Shape shape = ShapeFactory.createShape(type);
        shape.draw(graphicsContext, Color.BLUE, , );
    }*/
    public void initialize() {
        canvas = new Canvas();
        canvasHolder.getChildren().add(canvas);

        ballSpeed = 1;
        ballAcceleration = 1.5;

        racketWidth = 25;
        racketHeight = 200;

        winCondition = 5;

        canvas.widthProperty().bind(canvasHolder.widthProperty());
        canvas.heightProperty().bind(canvasHolder.heightProperty());
        player1Name.setText(playerName(1));
        player2Name.setText(playerName(2));
        player1score.setText("0");
        player2score.setText("0");
        applyFont(font, player1Name,player2Name,player1score,player2score);
        drawGame();
        canvas.widthProperty().addListener(e -> drawGame());
        canvas.heightProperty().addListener(e -> drawGame());
    }

    private void applyFont(Font font, Label... labels) {
        for (Label label: labels){
            label.setFont(font);
        }
    }

    private String playerName (int playerNum) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter player " + playerNum + " name");
        dialog.setContentText("Enter your name");
        String name = dialog.showAndWait().orElse("John Doe");
        if (name.isEmpty()) name = "Player" + playerNum;

        return name;
    }

    @FXML
    protected void changePlayer1 () {
        player1Name.setText(playerName(1));
    }

    @FXML
    protected void changePlayer2 () {
        player2Name.setText(playerName(2));
    }

    private void setSpeed(String speed) {
        switch (speed) {
            case "easy":
                ballSpeed = 0.5;
                break;
            case "medium" :
                ballSpeed = 1;
                break;
            case "hard" :
                ballSpeed = 1.5;
                break;
        }
    }
    @FXML
    protected void speedEasy() {
        setSpeed("easy");
    }

    @FXML
    protected void speedMedium() {
        setSpeed("medium");
    }

    @FXML
    protected void speedHard() {
        setSpeed("hard");
    }

    private void setRacketHeight(String length) {
        switch (length) {
            case "long" :
                racketHeight = 250;
                break;
            case "medium" :
                racketHeight = 200;
                break;
            case "short" :
                racketHeight = 150;
                break;
        }
        drawGame();
    }

    @FXML
    protected void setLengthLong() {
        setRacketHeight("long");
    }

    @FXML
    protected void setLengthMedium() {
        setRacketHeight("medium");
    }

    @FXML
    protected void setLengthShort() {
        setRacketHeight("short");
    }

    private void setRacketWidth(String width) {
        switch (width) {
            case "thin" :
                racketWidth = 15;
                break;
            case "medium" :
                racketWidth = 25;
                break;
            case "Thick" :
                racketWidth = 35;
                break;
        }
        drawGame();
    }

    @FXML
    protected void setWidthThin() {
        setRacketWidth("thin");
    }

    @FXML
    protected void setWidthMedium() {
        setRacketWidth("medium");
    }

    @FXML
    protected void setWidthThick() {
        setRacketWidth("Thick");
    }

    @FXML
    protected void setWinCondition() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set Win Condition");
        dialog.setContentText("Enter goals needed");
        String temp = dialog.showAndWait().orElse("5");
        if (temp.isEmpty()) winCondition = 5;

        else {
            winCondition = Integer.parseInt(temp);
        }
    }

    private void setBallAcceleration(String acceleration) {
        switch (acceleration){
            case "slow" :
                ballAcceleration = 1.25;
                break;
            case "medium" :
                ballAcceleration = 1.5;
                break;
            case "fast" :
                ballAcceleration = 1.75;
                break;
        }
    }

    @FXML
    protected void setAccelerationSlow() {
        setBallAcceleration("slow");
    }

    @FXML
    protected void setAccelerationMedium() {
        setBallAcceleration("medium");
    }

    @FXML
    protected void setAccelerationFast() {
        setBallAcceleration("fast");
    }

    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());

        double leftX = canvas.getWidth() / 10;
        double rightX = canvas.getWidth() * 9 / 10 - racketWidth;
        double centreX = canvas.getWidth()/2;
        double centreY = canvas.getHeight()/2;
        double bothY = (canvas.getHeight() - racketHeight) / 2;

        racket1.draw(gc, Color.GREEN,leftX,bothY, racketWidth, racketHeight);
        racket2.draw(gc, Color.TEAL, rightX, bothY, racketWidth, racketHeight);
        ball.draw(gc,Color.BLACK,centreX, centreY, 20, 20);

    }

    public void exitGame() {
        Platform.exit();
    }
}
