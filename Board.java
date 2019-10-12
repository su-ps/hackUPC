package Model;

public class Board {

    private Box[] boxes;

    public Board(){
        boxes = new Box[19];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box(Constants.relations[i]);
        }
    }

}
