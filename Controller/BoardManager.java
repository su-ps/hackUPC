package Controller;

import Model.Board;
import Model.Box;
import Model.Constants;

import java.util.Random;

public class BoardManager {

    public void emptyBoardGeneration(Board board){
        Random rnd = new Random();
        boolean check = false;

        int x = 0;
        int y = rnd.nextInt();

        do {
            x = rnd.nextInt();
            if (x<board.getBoxes()[y].length){
                check=true;
            }
        }while(!check);

        board.getBoxes()[y][x].setValue(Constants.newValue());
    }

    static void addNewValue(){

    }
}
