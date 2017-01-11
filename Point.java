//*****************************************************

// Edwin Lo    Jan 16th 2015

//

// Purpose: This class initializes the point object and declares final fields that corresponds to which type a point on the game board can be.

//  List of methods and their return value

// Point -basic constructor for the point object
//******************************************************
public class Point {

    //declaring fields
    final static int EMPTY = 0;
    final static int FOOD = 1;
    final static int SPECIAL_FOOD = 2;
    final static int SNAKE_BODY = 3;
    final static int WALL = 4;

    int x, y;

    //constructor
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}


