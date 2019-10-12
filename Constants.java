package Model;

public class Constants {

    static int[][] relations = new int[][] {
            {1, 3, 4, -1, -1, -1},      //0
            {0, 2, 4, 5, -1, -1},       //1
            {1, 5, 6, -1, -1, -1},      //2
            {0, 4, 7, 8, -1, -1},       //3
            {0, 1, 3, 5, 8, 9},         //4
            {1, 2, 4, 6, 9, 10},        //5
            {2, 5, 10, 11, -1, -1},     //6
            {3, 8, 12, -1, -1, -1},     //7
            {3, 4, 7, 9, 12, 13},       //8
            {4, 5, 8, 10, 13, 14},      //9
            {5, 6, 9, 11, 14, 15},      //10
            {6, 10, 15, -1, -1, -1},    //11
            {7, 8, 13, 16, -1, -1},     //12
            {8, 9, 12, 13, 16, 17},     //13
            {9, 10, 13, 15, 17, 18},    //14
            {10, 11, 14, 18, -1, -1},   //15
            {12, 13, 17, -1, -1, -1},   //16
            {13, 14, 16, 18, -1, -1},   //17
            {14, 15, 17, -1, -1, -1}    //18
    };

    public static int newValue(){
        if (Math.random()>0.9){
            return 4;
        }else{
            return 2;
        }
    }
}
