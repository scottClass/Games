/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author johns6971
 */
// make sure you rename this class if you are doing a copy/paste
public class Pong extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int x = 30;
    int play2Place = 740;
    int y = 200;
    int speed = 5;
    boolean up = false;
    boolean down = false;
    boolean right = false;
    boolean left = false;
    boolean up1 = false;
    boolean down1 = false;
    int place = 200;
    int move = 0;
    int moveDown = 1;
    int pieceX = 400;
    int pieceY = 250;
    int play1 = 0;
    int play2 = 0;
    String play1Score = play1 + "";
    String play2Score = play2 + "";

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
        g.setColor(Color.white);
        g.fillRect(play2Place, y, 20, 150);
        g.fillRect(x, place, 20, 150);
        g.drawString(play1Score, 750, 15);
        g.drawString(play2Score, 50, 15);

        g.fillRect(pieceX, pieceY, 10, 10);


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

            //move horizontal
            if (move == 0) {
                pieceX -= 8;
            }
            if (move == 1) {
                pieceX += 8;
            }

            //Player collision
            if (pieceX == x + 20 && pieceY > place && pieceY < place + 150) {
                move = 1;
                if(pieceY < y + 50) {
                    moveDown = 2;
                }
                if(pieceY > y + 50) {
                    moveDown = 1;
                }
            }
            if (pieceX + 10 == play2Place && pieceY > y && pieceY < y + 150) {
                move = 0;
                if(pieceY < y + 50) {
                    moveDown = 2;
                }
                if(pieceY > y + 50) {
                    moveDown = 1;
                }
            }
            
            
            
            //move vertical
            if (moveDown == 1) {
                pieceY += 1;
            }
            if (moveDown == 2) {
                pieceY -= 1;
            }


            //Score
            if (pieceX == 800) {
                play2 += 1;
                pieceX = 400;
                pieceY = 250;
                play2Score = play2 + "";
                move = 0;
            }

            if (pieceX == 0) {
                play1 += 1;
                pieceX = 400;
                pieceY = 250;
                play1Score = play1 + "";
                move = 1;
            }



            //Player movement
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
            if (up1) {
                place -= speed;
            }
            if (down1) {
                place += speed;
            }

            
            //boarders
            if (y < 0) {
                y = 0;
            }
            if (y + 150 > HEIGHT) {
                y = HEIGHT - 150;
            }
            if (place < 0) {
                place = 0;
            }
            if (place + 150 > HEIGHT) {
                place = HEIGHT - 150;
            }

            //Play piece boarders
            if (pieceY == 0) {
                pieceY = 10;
                moveDown = 1;
            }
            if (pieceY == 750) {
                moveDown = 2;
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
        Pong game = new Pong();
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
        }

        int Key = e.getKeyCode();
        if (Key == KeyEvent.VK_W) {
            up1 = true;
        } else if (Key == KeyEvent.VK_S) {
            down1 = true;
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
        }
        int Key = e.getKeyCode();
        if (Key == KeyEvent.VK_W) {
            up1 = false;
        } else if (Key == KeyEvent.VK_S) {
            down1 = false;
        }




    }
}
