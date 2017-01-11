//*****************************************************

// Edwin Lo    Jan 16th 2015

//

// Purpose: This class is responsible for initializing a window that reads the user's input and starting the snake game.

//  List of methods and their return value

// main - returns nothing
// keyPressed - returns nothing
// keyReleased - returns nothing
// keyTyped - returns nothing
//******************************************************
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;

public class SneakySnakeWindow extends JFrame implements KeyListener {

    //declaring fields
    static final int WIDTH = 20;
    static final int HEIGHT = 20;

    public SneakySnakeWindow(String title) {
        super(title);
        getContentPane().setLayout(null);
        addKeyListener(this);
    }

    public static void main(String args[]) throws InterruptedException, IOException {

        // Instantiate a FirstApplication object so you can display it
        SneakySnakeWindow frame =  new SneakySnakeWindow("Snake Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size of the application window (frame)
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true); // Show the application (frame)
        Snake.start();

    }

    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        switch( keyCode ) {
            case KeyEvent.VK_UP:
                if (Snake.direction != Snake.DOWN) Snake.direction = Snake.UP;
                Snake.update();
                //up
                break;
            case KeyEvent.VK_DOWN:
                if (Snake.direction != Snake.UP) Snake.direction = Snake.DOWN;
                Snake.update();
                //down
                break;
            case KeyEvent.VK_LEFT:
                if (Snake.direction != Snake.RIGHT) Snake.direction = Snake.LEFT;
                Snake.update();
                //left
                break;
            case KeyEvent.VK_RIGHT :
                if (Snake.direction != Snake.LEFT) Snake.direction = Snake.RIGHT;
                Snake.update();
                //right
                break;
            default: Snake.update();
        }
    }

    public void keyReleased(KeyEvent event) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
}




