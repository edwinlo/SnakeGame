//*****************************************************

// Edwin Lo    Jan 16th 2015

//

// Purpose: This class initializes the snake object and creates a linked list of "Point" objects. It also allows the movement of the snake on the map,
//			the increase in the snake's length with the interaction of food, and what will happen when the snake touches the wall of the game board.

//  List of methods and their return value

// move - returns nothing
// getNext - returns a point object
// checkLose - returns nothing
// update - returns nothing
// start - returns nothing
//******************************************************
import java.util.*;

public class Snake {

    //declaring fields
    public static int direction;
    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;

    private static LinkedList<Point> snake;
    private static boolean lose;
    private static Point head;

    //constructor
    public Snake(){
        //initializing the linked list
        snake = new LinkedList<>();

        //placing the snake on the middle of the game board with a length of 3
        for (int a = 2; a != -1; a --){
            head = new Point(GameBoard.width/2, GameBoard.height/2 - a);
            snake.addFirst(head);		//adds the position of the snake head to the first node of the linked list
        }

    }

    //moves the snake to the next position
    public static void move(Point nextPos) {

        //removes the last node of the snake
        Point tail = snake.removeLast();
        GameBoard.map[tail.x][tail.y] = Point.EMPTY;

        //adds the next position to the front
        head = nextPos;
        snake.addFirst(head);

        //moves the rest of the body
        for (Point index : snake){
            GameBoard.map[index.x][index.y] = Point.SNAKE_BODY;
        }
    }

    //gets the next position of the snake
    private static Point getNext(Point currentPos) {
        //new x,y positions
        int x = currentPos.x;
        int y = currentPos.y;

        //moves changes the x or y coordinate depending on where the snake is facing
        switch(direction){
            case    UP:
                x--;		//up
                break;
            case  DOWN:
                x++;		//down
                break;
            case  LEFT:
                y--;		//left
                break;
            case RIGHT:
                y++;		//right
                break;
        }
        //initializes the next point
        Point nextPos = new Point(x,y);

        //returns the next position
        return nextPos;
    }

    //checks if the game is over or not
    public static void checkLose(Point nextPos){
        int next  = GameBoard.map[nextPos.x][nextPos.y];

        //if the next position is a snake body
        if (next == Point.SNAKE_BODY){
            lose = true;
        }
        //if the next position is a wall
        if (next == Point.WALL){
            lose = true;
        }
    }

    //updates the game board
    public static void update(){
        Point nextPos = getNext(head);
        checkLose(nextPos);

        //if the next position was a food
        if (GameBoard.map[nextPos.x][nextPos.y] == Point.FOOD){
            snake.addFirst(head);			//adds one unit to the snake body
            GameBoard.generateFood();
        }

        //if the next position was a special food
        if (GameBoard.map[nextPos.x][nextPos.y] == Point.SPECIAL_FOOD){
            snake.addFirst(head);
            nextPos = getNext(head);
            snake.addFirst(head);			//adds two units to the snake body
            GameBoard.generateFood();

        }

        //moves the snake to the next position
        move(nextPos);
    }

    //starts the game
    public static void start() throws InterruptedException {
        int score;
        String userChoice;
        Scanner scan = new Scanner(System.in);			//initializing the scanner object for user input

        do{
            score = 0;
            lose = false;
            GameBoard gb = new GameBoard();				//initializing a game board object
            gb.run();

            //while the game is not over
            while(!lose){
                //updates the map
                update();
                //for spacing out each "game board"
                System.out.println("\n\n\n\n\n\n\n\n\n");
                //prints out the map
                gb.printMap();

                //updates the score
                score = snake.size() - 3;

                //doubles the refresh rate every 5 points
                int time = 500 - ((score / 5) * 50);
                Thread.sleep(time);
            }

            //prints out the following
            System.out.println("Game over!! You Lose!!");
            System.out.println("Your score is: " + score);
            //asks the user if he/she wants to play again
            System.out.println("Do you want to play again? (y/n)");
            userChoice = scan.next();

        }while(userChoice.equals("y"));			//runs while the user wants to keep playing
        scan.close();
    }

}


