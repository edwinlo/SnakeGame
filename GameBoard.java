//*****************************************************

// Edwin Lo    Jan 16th 2015

//

// Purpose: This class initializes the game board of a snake game, randomly generates food on the map, and prints out the game board with walls, food, and
//			the snake.

//  List of methods and their return value

// initMap - returns nothing
// generateFood - returns nothing
// run - returns nothing
// printMap - returns nothing
// getMapValue - returns a character
//******************************************************

import java.util.Random;

public class GameBoard {

    //declaring fields
    public static int height = 20;
    public static int width = 20;

    public static int[][] map;

    //constructor
    public GameBoard (){
        map = new int[width][height];

        //giving all the locations on the map the value of 0
        for (int x = 0; x < width; x ++){
            for (int y = 0; y < height; y ++) {
                map [x][y] = Point.EMPTY;
            }
        }
    }

    //finds the walls of the map
    public static void initMap(){

        //places the top and bottom walls
        for (int x = 0; x < width; x++) {
            map[x][0] = Point.WALL;
            map[x][height -1] = Point.WALL;
        }
        //places the right and left walls
        for (int y = 0; y < height; y++) {
            map[0][y] = Point.WALL;
            map[width - 1][y] = Point.WALL;
        }
    }

    //generates the food
    public static void generateFood(){
        Random rand = new Random();

        int x = 0;
        int y = 0;
        int chance;

        do{
            //randomly generates the position
            x = rand.nextInt(width - 2) + 1;
            y = rand.nextInt(height - 2) + 1;

        } while (map[x][y] != Point.EMPTY);			//loops until an empty spot is found

        //for increasing the chance of getting a normal food than a special food
        chance = rand.nextInt(4) + 1;

        if (chance == 1 || chance == 2 || chance == 3){			//normal food
            map[x][y] = Point.FOOD;
        }
        else if (chance == 4){									//special food
            map[x][y] = Point.SPECIAL_FOOD;
        }
    }

    //initializes the map
    public void run(){
        initMap();
        generateFood();
        new Snake();
    }

    //prints out the map
    public static void printMap(){
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                //prints out the corresponding character at the x, y position
                System.out.print(getMapValue(map[x][y]));
            }
            //starts a new line for each x value increment
            System.out.println();
        }
    }

    //for getting the corresponding character for each position on the map
    public static char getMapValue(int value){
        // Returns a part of snake body
        switch (value) {
            case Point.FOOD:
                //returns a food
                return 'x';
            case Point.SPECIAL_FOOD:
                //returns a special food
                return '@';
            case Point.WALL:
                //returns a wall
                return '*';
            case Point.SNAKE_BODY:
                //returns a snake body
                return 'o';
            case Point.EMPTY:
                //returns an empty space
                return ' ';
            default:
                //returns an empty space
                return ' ';
        }
    }
}







