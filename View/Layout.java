package View;

import Controller.Main;
import Model.Direction;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.io.File;
import java.util.Arrays;

public class Layout {

    BorderPane borderPane;
    StackPane game;
    GridPane buttons;
    Scene scene;
    Stage window;

    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        borderPane = new BorderPane();
        game = new StackPane();
        buttons = new GridPane();
        game.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        setBoard();
        setButtons();

        buttons.setHgap(10);
        buttons.setVgap(10);
        buttons.setPadding(new Insets(500,100,0,0));

        borderPane.setCenter(game);
        borderPane.setRight(buttons);
        scene = new Scene(borderPane);

        window.setScene(scene);
        window.setTitle("2048");
        window.setMaximized(true);

        getNewValues();
        checkHexagons();

        window.show();

    }

    private void setBoard(){

        for (int i=-1; i<2; i++){
            createHexagon(-2,i);
        }
        for (int i=-1; i<3; i++){
            createHexagon(-1,i);
        }
        for (int i=-2; i<3; i++){
            createHexagon(0,i);
        }
        for (int i=-1; i<3; i++){
            createHexagon(1,i);
        }
        for (int i=-1; i<2; i++){
            createHexagon(2,i);
        }

    }

    private void createHexagon(int row, int num){
        Hexagon hexagon = new Hexagon();
        Text number = new Text(Integer.toString(hexagon.value));
        if (row%2==0) {
            hexagon.setTranslateX(85*num);
            number.setTranslateX(85*num);
        }else {
            hexagon.setTranslateX(85*num-42.5);
            number.setTranslateX(85*num-42.5);
        }
        hexagon.setTranslateY(row*75);
        number.setTranslateY(row*75);
        hexagon.setStyle("-fx-fill:white;-fx-stroke:black;-fx-stroke-width:5");
        if (hexagon.value==0) hexagon.setStyle("-fx-fill:grey;-fx-stroke:black;-fx-stroke-width:5");
        number.setStyle("-fx-font-size:50;");

        if (hexagon.value==0) number.setText("");

        game.getChildren().addAll(hexagon,number);

    }

    public void checkHexagons(){
        for (int i=0; i<game.getChildren().toArray().length; i++){
            if (i%2==0){
                ((Hexagon)game.getChildren().toArray()[i]).resetStyle();
                if (((Hexagon)game.getChildren().toArray()[i]).value==0) ((Text)game.getChildren().get(i+1)).setText("");
                else {
                    ((Text)game.getChildren().toArray()[i+1]).setText(Integer.toString(((Hexagon)game.getChildren().toArray()[i]).value));
                }
            }
        }
    }

    public void setButtons(){

        Direction[] directions = {
                Direction.TopLeft, Direction.TopRight,
                Direction.Left, Direction.Right,
                Direction.BotLeft, Direction.BotRight};

        for (int i=0; i<3; i++){
        for (int j=0; j<2; j++) {
            Button button = new Button();

            //button.setGraphic(new ImageView(new Image("files"+ File.separator+directions[2*i+j].name()+".png")));
            final int it = 2*i+j;
            button.setOnAction(event -> {
                Main.board.newMovement(directions[it]);
                getNewValues();
                checkHexagons();
                boolean game = Main.board.isGame();
                if (!game) finish();
            });
            GridPane.setConstraints(button, j, i);
            buttons.getChildren().add(button);
        }
        }

    }

    private void getNewValues(){

        int[] values = Main.board.getAllValues();
        int counter = 0;

        for (int i=0; i<game.getChildren().toArray().length; i++){
            if (i%2==0){
                if (values[counter]==2048) win();
                ((Hexagon)game.getChildren().toArray()[i]).value = values[counter];
                counter++;
            }
        }

    }

    private void win(){
        printMessage("Win!!!");
        System.exit(0);
    }

    private void finish(){
        printMessage("Lost!!!");
        System.exit(0);
    }

    public void printMessage(String message){
        Label label = new Label(message);
        label.setStyle("-fx-font-size:200");
        Popup popup = new Popup();
        popup.getContent().add(label);
        popup.centerOnScreen();
        popup.show(window);
        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(event -> {
            popup.hide();
            wait.playFromStart();
        });
        wait.play();
    }

}