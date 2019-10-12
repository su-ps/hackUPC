package Model;

public class Box {

    private int value;


    public Box(){
        this.value = 0;
    }

    public void setValue(){
        if (this.value==0){
            this.value++;
        }else{
            this.value*=2;
        }
    }

    public void setValue(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public boolean canMove(int x1, int y1, int x2, int y2, int length){
        if (x2 < length && x2 >= 0 && y2 >= 0 && y2 < 5) {
            return !((y2 == y1 - 1 && x2 == x1 + 1) || (y2 == y1 + 1 && x2 == x1 + 1));
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "["+this.value+"]";
    }
}
