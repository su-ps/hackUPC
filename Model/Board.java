package Model;

import java.util.Random;

public class Board {

    private Box[][] boxes;

    public Board(){
        boxes = new Box[][] {
                {new Box(Constants.movementDataSet[0]), new Box(Constants.movementDataSet[1]), new Box(Constants.movementDataSet[2])},
                {new Box(Constants.movementDataSet[3]), new Box(Constants.movementDataSet[4]), new Box(Constants.movementDataSet[5]), new Box(Constants.movementDataSet[6])},
                {new Box(Constants.movementDataSet[7]), new Box(Constants.movementDataSet[8]), new Box(Constants.movementDataSet[9]), new Box(Constants.movementDataSet[10]), new Box(Constants.movementDataSet[11])},
                {new Box(Constants.movementDataSet[12]), new Box(Constants.movementDataSet[13]), new Box(Constants.movementDataSet[14]), new Box(Constants.movementDataSet[15])},
                {new Box(Constants.movementDataSet[16]), new Box(Constants.movementDataSet[17]), new Box(Constants.movementDataSet[18])}
        };

        emptyBoardGeneration();
        emptyBoardGeneration();
    }

    private void emptyBoardGeneration(){
        Random rnd = new Random();
        boolean check = false;

        int x = 0;
        int y = rnd.nextInt(5);

        do {
            x = rnd.nextInt(5);
            if (x<boxes[y].length&&boxes[y][x].getValue()==0){
                check=true;
            }
        }while(!check);

        boxes[y][x].setValue(Constants.newValue());
    }   //comprovat

    public void newValue(Direction direction){

        Random rnd = new Random();
        int[][] possiblePositions;
        int[][] emptyPositions;
        int counter=0;

        switch(direction){
            case Left:
                possiblePositions = new int[][] {{0,2}, {1,3}, {2,4}, {3,3}, {4,2}};
                break;
            case Right:
                possiblePositions = new int[][] {{0,0}, {1,0}, {2,0}, {3,0}, {4,0}};
                break;
            case BotLeft:
                possiblePositions = new int[][] {{0,0}, {0,1}, {0,2}, {1,3}, {2,4}};
                break;
            case TopLeft:
                possiblePositions = new int[][] {{2,4}, {3,3}, {4,2}, {4,1}, {4,0}};
                break;
            case BotRight:
                possiblePositions = new int[][] {{0,2}, {0,1}, {0,0}, {1,0}, {2,0}};
                break;
            case TopRight:
                possiblePositions = new int[][] {{2,0}, {3,0}, {4,0}, {4,1}, {4,2}};
                break;
            default:
                possiblePositions=null;
        }

        for (int[] position : possiblePositions) {
            if (boxes[position[0]][position[1]].getValue() == 0) {
                counter++;
            }
        }

        emptyPositions = new int[counter][2];
        counter=0;
        for (int[] position : possiblePositions) {
            if (boxes[position[0]][position[1]].getValue() == 0) {
                emptyPositions[counter] = position;
                counter++;
            }
        }

        int[] winningPosition = emptyPositions[rnd.nextInt(emptyPositions.length)];
        boxes[winningPosition[0]][winningPosition[1]].setValue(Constants.newValue());

    } //comprovat

    public void newMovement(Direction direction){

        switch (direction){
            case TopRight:
            case TopLeft:
            case Left:
                for (int i = 0; i < boxes.length; i++) {
                    for (int j = 0; j < boxes[i].length; j++) {
                        move(boxes[i][j], direction, i, j);
                    }
                }
                break;
            case BotRight:
            case BotLeft:
            case Right:
                for (int i = boxes.length-1; i >= 0; i--) {
                    for (int j = boxes[i].length-1; j >= 0; j--) {
                        move(boxes[i][j], direction, i, j);
                    }
                }
                break;
        }
        newValue(direction);

    } //comprovat

    private void move(Box box, Direction direction, int y, int x){


        do {
            int[] availableMovement = box.getMoveset(direction);
            if (availableMovement[0]!=-1){
                Box newBox = boxes[availableMovement[0]][availableMovement[1]];
                if (newBox.getValue()>0){
                    if (box.getValue()==newBox.getValue()){
                        merge(x, y, availableMovement[1], availableMovement[0]);

                    }else{
                        break;
                    }
                }else{
                    int value = box.getValue();
                    boxes[availableMovement[0]][availableMovement[1]].setValue(value);
                    boxes[y][x].setToZero();

                    box = newBox;
                    y = availableMovement[0];
                    x = availableMovement[1];
                }
            }else{
                break;
            }

        }while(true);

    }   //revisat

    public int[] getAllValues(){
        int[] allValues = new int[19];
        int counter = 0;
        for (Box[] box : boxes) {
            for (Box value : box) {
                allValues[counter] = value.getValue();
                counter++;
            }
        }
        return allValues;
    }

    public Box[][] getBoxes(){
        return this.boxes;
    }

    @Override
    public String toString(){
        String ret="";
        for (Box[] boxRow : boxes){
            for (Box box : boxRow){
                ret = ret.concat(box.toString());
            }
            ret = ret.concat("\n");
        }
        return ret;
    }

    private void merge(int x1, int y1, int x2, int y2){
        if ((boxes[y1][x1].getValue()==boxes[y2][x2].getValue())&&(boxes[y1][x1].getValue()!=0&&boxes[y2][x2].getValue()!=0)){
            boxes[y1][x1].setValue(0);
            boxes[y2][x2].setValue();
        }
    }


}
