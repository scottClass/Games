/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.KeyEvent;

/**
 *
 * @author johns6971
 */
// make sure you rename this class if you are doing a copy/paste
public class Graphic extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int x = 30;
    int y = 270;
    int z = 50;
    int pac = x + 225;
    int pac2 = x + 360;
    int turn = 0;
    int move = 0;
    int rotate = 0;
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);

        g.setColor(Color.YELLOW);

        //drawArc(x,y,width,height,startTheta,rotateTheta)
        g.fillArc(z, 250, 100, 100, x, y);

        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(z != 700 && move == 0) {
                z = z + 5;
            }
            if(z == 700 && move == 0) {
                move = 1;
            }
            if(z != 50 && move == 1) {
                z = z - 5;
                x = 200;
            }
            if(z == 50 && move == 1) {
                move = 0;
                x = 30;
            }
            
            if(move == 1 && y != 360 && rotate == 0) {
                y += 5;
            }
            if(y == 360 && rotate == 0 && move == 1) {
                rotate = 1;
            }
            if(rotate == 1 && y != 270 && move == 1) {
                y -= 5;
            }
            if(y == 270 && rotate == 1 && move == 1) {
                rotate = 0;
            }
            
            
            if(y != 360 && rotate == 0 && move == 0) {
                y += 5;
            }
            if(y == 360 && rotate == 0 && move == 0) {
                rotate = 1;
            }
            if(rotate == 1 && y != 270 && move == 0) {
                y -= 5;
            }
            if(y == 270 && rotate == 1 && move == 0) {
                rotate = 0;
            }
            
            
            
            
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game ");

        // creates an instance of my game
        Graphic game = new Graphic();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // starts my game loop
        game.run();
    }
}
