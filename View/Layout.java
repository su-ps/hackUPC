package View;

import Controller.Main;
import Model.Direction;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;

public class Layout {

    BorderPane borderPane;
    StackPane game;
    StackPane buttons;
    Scene scene;

    public void start(Stage primaryStage) throws Exception {

        Stage window = primaryStage;

        borderPane = new BorderPane();
        game = new StackPane();
        buttons = new StackPane();
        game.setAlignment(Pos.CENTER);

        setBoard();
        setButtons();

        borderPane.setCenter(game);
        borderPane.setRight(buttons);
        scene = new Scene(borderPane);

        window.setScene(scene);
        window.setTitle("Depen de lo que vulguis");
        window.setMaximized(true);

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

        game.getChildren().add(hexagon);
        if (hexagon.value!=0) game.getChildren().add(number);
    }

    public void checkHexagons(){
        for (int i=0; i<game.getChildren().toArray().length; i++){
            if (i%2!=0){
                ((Hexagon)game.getChildren().toArray()[i]).resetStyle();
                if (((Hexagon)game.getChildren().toArray()[i]).value==0) game.getChildren().remove(i+1);
                else {
                    ((Text)game.getChildren().toArray()[i]).setText(Integer.toString(((Hexagon)game.getChildren().toArray()[i]).value));
                }
            }
        }
    }

    public void setButtons(){

        Direction[] directions = {
                Direction.TopLeft, Direction.TopRight,
                Direction.Left, Direction.Right,
                Direction.BotLeft, Direction.BotRight};

        for (int i=0; i<6; i++){

            Button  button = new Button();
            //button.setGraphic(new ImageView(new Image("files"+ File.separator+directions[i].name()+".png")));
            final int it = i;
            button.setOnAction(event->Main.board.newMovement(directions[it]));
            buttons.getChildren().add(button);

        }

    }

}