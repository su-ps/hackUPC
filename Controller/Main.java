package Controller;

import Model.Board;
import Model.Direction;
import javafx.application.Application;
import View.Layout;

public class Main extends Application {

    Layout layout = new Layout();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        layout.start(primaryStage);
    }

    public static Board board = new Board();

    public void run(){

    }

}
