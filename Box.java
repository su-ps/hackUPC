package Model;

public class Box {

    private final int[] relations;
    private int value;

    public Box(int[] relations){
        this.relations = relations;
        this.value = 0;
    }

    public void setValue(){
        if (this.value==0){
            this.value++;
        }else{
            this.value*=2;
        }
    }

    public int[] getRelations() {
        return relations;
    }

    public int getValue() {
        return value;
    }
}
