package View;

import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {

    public int value = 0;

    public Hexagon(){
        this.getPoints().addAll(new Double[]{
                43.3, 0.0,
                0.0, 25.0,
                0.0, 75.0,
                43.3, 100.0,
                86.6, 75.0,
                86.6, 25.0});
    }

    public void resetStyle(){
        if (value==0) this.setStyle("-fx-fill:grey;-fx-stroke:black;-fx-stroke-width:5");
        else this.setStyle("-fx-fill:white;-fx-stroke:black;-fx-stroke-width:5");
    }

    @Override
    public String toString() {
        return "Hexagon{" +
                "value=" + value +
                '}';
    }
}