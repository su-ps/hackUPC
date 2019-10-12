package Model;

import java.util.HashMap;

public class Box {

    private int value;
    private HashMap<Direction , int[]> movementHashMap;

    Box(int[][] fullMoveset){
        this.value = 0;
        movementHashMap = new HashMap<>();
        movementHashMap.put(Direction.Right, fullMoveset[0]);
        movementHashMap.put(Direction.Left, fullMoveset[1]);
        movementHashMap.put(Direction.TopRight, fullMoveset[2]);
        movementHashMap.put(Direction.TopLeft, fullMoveset[3]);
        movementHashMap.put(Direction.BotRight, fullMoveset[4]);
        movementHashMap.put(Direction.BotLeft, fullMoveset[5]);
    }

    void setValue(){
        if (this.value==0){
            this.value++;
        }else{
            this.value*=2;
        }
    }

    void setValue(int value){
        this.value=value;
    }

    public int[] getMoveset(Direction direction){
        return this.movementHashMap.get(direction);
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "["+this.value+"]";
    }
}
