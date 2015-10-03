/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author johns6971
 */
// make sure you rename this class if you are doing a copy/paste
public class FlappyBird extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int dy = 0;
    int gravity = 3/2;

    //camera movement in x direction
    int camx = 0;

    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean jump = false;
    boolean inAir = false;

    boolean gameOver = false;
    boolean start = false;
    boolean pressed = false;
    
    Rectangle player = new Rectangle(100, 150, 50, 50);
    Rectangle block = new Rectangle(1000, 500, 50, 200);
    Rectangle block2 = new Rectangle(1000, 0, 50, 200);

    
    Rectangle ground = new Rectangle(0, 550, 1000000000, 50);
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        g.setColor(Color.YELLOW);
        g.fillRect(player.x - camx, player.y, player.width, player.height);

        g.setColor(Color.BLACK);
        g.fillRect(block.x - camx, block.y, block.width, block.height);
        g.fillRect(block2.x - camx, block2.y, block2.width, block2.height);

        g.setColor(Color.GREEN);
        g.fillRect(ground.x - camx, ground.y, ground.width, ground.height);
        
        if (gameOver) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("Game Over", 400, 300);
        }
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
            dy = dy + gravity;
            
            
            if (!gameOver) {
                player.x += 10;
                if (jump && !pressed) {
                    start = true;
                    pressed = true;
                    dy = - 20;
                }
                if(!jump) {
                    pressed = false;
                }
            }
                player.y += dy;

            
            if (player.intersects(block)) {
                gameOver = true;
                handleCollision(player, block);
            } else if (player.intersects(block2)) {
                gameOver = true;
                handleCollision(player, block2);
            }
            
            if(player.y < 0) {
                player.y = 0;    
            }
            if (player.y > 500) {
                player.y = 500;
                gameOver = true;
            }

            //do camera correction
            if (player.x < WIDTH / 2) {
                //no camera correction
                camx = 0;
            } else {
                camx = player.x - WIDTH / 2;
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
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        FlappyBird game = new FlappyBird();
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

        frame.addKeyListener(game);

        // starts my game loop
        game.run();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_UP) {
            up = true;
        } else if (KeyCode == KeyEvent.VK_DOWN) {
            down = true;
        } else if (KeyCode == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (KeyCode == KeyEvent.VK_LEFT) {
            left = true;
        } else if (KeyCode == KeyEvent.VK_SPACE) {
            jump = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (KeyCode == KeyEvent.VK_LEFT) {
            left = false;
        } else if (KeyCode == KeyEvent.VK_UP) {
            up = false;
        } else if (KeyCode == KeyEvent.VK_DOWN) {
            down = false;
        } else if (KeyCode == KeyEvent.VK_SPACE) {
            jump = false;
        }

    }

    public void handleCollision(Rectangle player, Rectangle block) {
        //make overlap rectangle
        Rectangle overlap = player.intersection(block);

        //if hieght is bigger
        if (overlap.height > overlap.width) {
            //player is on the left
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            dy = 0;
            if (player.y < block.y) {
                player.y -= overlap.height;
                inAir = false;
            } else if (player.y + player.height > block.y + block.height) {
                player.y += overlap.height;
            }
        }
    }
}
