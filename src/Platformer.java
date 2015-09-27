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
public class Platformer extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    int dy = 0;
    int gravity = 2;
    int health = 50;

    //camera movement in x direction
    int camx = 0;
    
    boolean right = false;
    boolean left = false;
    boolean jump = false;
    boolean powerDown = false;
    boolean inAir = false;
    boolean gameOver = false;
    int lives = 5;

    int level = 1;

    Rectangle player = new Rectangle(100, 400, health, health);
    //level 1 objects
    Rectangle block = new Rectangle(0, 500, 1000, 50);
    Rectangle block2 = new Rectangle(1200, 400, 500, 50);
    Rectangle block3 = new Rectangle(1800, 300, 500, 50);
    Rectangle block4 = new Rectangle(2600, 400, 500, 50);
    Rectangle block5 = new Rectangle(3300, 500, 1000, 50);
    Rectangle block6 = new Rectangle(3500, 250, 350, 50);
    //level 1 enemies
    Rectangle enemy = new Rectangle(1400, 350, 50, 50);
    Rectangle enemy2 = new Rectangle(2900, 350, 50, 50);
    Rectangle enemy3 = new Rectangle(3000, 350, 50, 50);
    //level 1 powerup

    Rectangle powerup = new Rectangle(3625, 100, 30, 30);
    
    Rectangle end = new Rectangle(4000, 400, 20, 100);

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        Color playerColor = new Color(219, 33, 33);

        g.setColor(playerColor);
        g.fillRect(player.x - camx, player.y, player.width, player.height);
        if (level == 1) {
            g.setColor(Color.BLACK);
            g.fillRect(block.x - camx, block.y, block.width, block.height);
            g.fillRect(block2.x - camx, block2.y, block2.width, block2.height);
            g.fillRect(block3.x - camx, block3.y, block3.width, block3.height);
            g.fillRect(block4.x - camx, block4.y, block4.width, block4.height);
            g.fillRect(block5.x - camx, block5.y, block5.width, block5.height);
            g.fillRect(block6.x - camx, block6.y, block6.width, block6.height);
            g.setColor(Color.green);
            g.fillRect(enemy.x - camx, enemy.y, enemy.width, enemy.height);
            g.fillRect(enemy2.x - camx, enemy2.y, enemy2.width, enemy2.height);
            g.fillRect(enemy3.x - camx, enemy3.y, enemy3.width, enemy3.height);
            g.setColor(Color.red);
            g.fillRect(powerup.x - camx, powerup.y, powerup.width, powerup.height);
            g.setColor(Color.black);
            g.fillRect(end.x - camx, end.y, end.width, end.height);
        }
        
        if(level == 2) {
            g.setColor(Color.black);
            g.fillRect(block.x - camx, block.y, block.width, block.height);
            g.fillRect(block2.x - camx, block2.y, block2.width, block2.height);
            g.fillRect(block3.x - camx, block3.y, block3.width, block3.height);
        }

        g.setColor(Color.black);
        g.drawString("X " + lives, 50, 50);
        g.setColor(playerColor);
        g.fillRect(20, 35, 20, 20);
        if (gameOver) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 900, 700);
            g.setColor(Color.white);
            g.drawString("Game Over", 370, 300);
            if(level == 1) {
                g.drawString("Wow you suck", 365, 320);
            }
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
            if (right && !gameOver) 
            {
                player.x += 10;
            } else if (left && !gameOver) {
                player.x -= 10;
            }
            if (jump && !inAir && !gameOver) 
            {
                dy = -30;
                inAir = true;
            }
            
            if(powerDown && player.height != 25 && player.width != 25) 
            {
                player.height -= 25;
                player.width -= 25;
            }

            player.y += dy;

            if (player.x < 0) 
            {
                player.x = 0;
            }

            //collisions for level 1
            if (level == 1) 
            {
                if (player.intersects(block)) {
                    handleCollision(player, block);
                } else if (player.intersects(block2)) {
                    handleCollision(player, block2);
                } else if (player.intersects(block3)) {
                    handleCollision(player, block3);
                } else if (player.intersects(block4)) {
                    handleCollision(player, block4);
                } else if (player.intersects(block5)) {
                    handleCollision(player, block5);
                } else if (player.intersects(block6)) {
                    handleCollision(player, block6);
                } else if (player.intersects(enemy)) {
                    handleAttack(player, enemy);
                } else if (player.intersects(enemy2)) {
                    handleAttack(player, enemy2);
                } else if (player.intersects(enemy3)) {
                    handleAttack(player, enemy3);
                } else {
                    inAir = true;
                }
                
                if (player.x == 4000) 
                {
                    level = 2;
                    player.x = 100;
                    block2.x = 350;
                    block2.y = 300;
                    block2.width = 200;
                    block2.height = 50;
                    block3.width = 50;
                    block3.height = 150;
                    block3.x = 900;
                    block3.y = 400;
                }
                
                
            }
            
            //collisions for level 2
             if (level == 2) 
             {
                if (player.intersects(block)) {
                    handleCollision(player, block);
                } else if (player.intersects(block2)) {
                    handleCollision(player, block2);
                } else if (player.intersects(block3)) {
                    handleCollision(player, block3);
                }
             }
             
            //taking damage/losing a life on level 1
            if (level == 1) 
            {
                if (player.x >= enemy.x && player.x <= enemy.x + 50
                        && player.y == enemy.y) 
                {
                    if (player.x == enemy.x + 50) 
                    {
                        player.x += 100;
                    } else {
                        player.x -= 100;
                    }
                    player.width -= 25;
                    player.height -= 25;
                } else if (player.x >= enemy.x && player.x <= enemy.x + enemy.width &&
                        player.width == 25 && player.height == 25 && enemy.y < player.y
                        && enemy.y != - 100) {
                        lives -= 1;
                        if (lives <= 0) 
                        {
                            gameOver = true;
                        } else {
                            player.x = 100;
                            player.y = 300;
                            player.height = 50;
                            player.width = 50;
                            enemy.y = 350;
                            enemy2.y = 350;
                            enemy3.y = 350;
                            powerup.y = 100;
                        }
                }
                if (player.x >= enemy2.x && player.x <= enemy2.x + 50
                        && player.y == enemy2.y) 
                {
                    if (player.x == enemy2.x + 50) 
                    {
                        player.x += 100;
                    } else {
                        player.x -= 100;
                    }
                    player.width -= 25;
                    player.height -= 25;
                } else if (player.x >= enemy2.x && player.x <= enemy2.x + enemy2.width &&
                        player.width == 25 && player.height == 25 && enemy2.y < player.y
                        && enemy2.y != - 100) {
                        lives -= 1;
                        if (lives <= 0) 
                        {
                            gameOver = true;
                        } else {
                            player.x = 100;
                            player.y = 300;
                            player.height = 50;
                            player.width = 50;
                            enemy.y = 350;
                            enemy2.y = 350;
                            enemy3.y = 350;
                            powerup.y = 100;
                        }
                }
                
                if (player.x >= enemy3.x && player.x <= enemy3.x + 50
                        && player.y == enemy3.y) 
                {
                    if (player.x == enemy3.x + 50) 
                    {
                        player.x += 100;
                    } else {
                        player.x -= 100;
                    }
                    player.width -= 25;
                    player.height -= 25;
                } else if (player.x >= enemy3.x && player.x <= enemy3.x + enemy3.width &&
                        player.width == 25 && player.height == 25 && enemy3.y < player.y
                        && enemy3.y != - 100) {
                        lives -= 1;
                        if (lives <= 0) 
                        {
                            gameOver = true;
                        } else {
                            player.x = 100;
                            player.y = 300;
                            player.height = 50;
                            player.width = 50;
                            enemy.y = 350;
                            enemy2.y = 350;
                            enemy3.y = 350;
                            powerup.y = 100;
                        }
                }
                
                if (player.x >= powerup.x && player.x <= powerup.x + powerup.width && 
                        player.y >= powerup.y && player.y <= powerup.y + powerup.height) 
                {
                    player.width += 25;
                    player.height += 25;
                    powerup.y = -50;
                }
                
            }

            
            
            //if player dies
            if (player.y > 600) 
            {
                lives -= 1;
                if (lives <= 0) 
                {
                    gameOver = true;
                } else {
                    player.x = 100;
                    player.y = 300;
                    player.height = 50;
                    player.width = 50;
                    enemy.y = 350;
                    enemy2.y = 350;
                    enemy3.y = 350;
                    powerup.y = 100;
                }

                inAir = true;
            }
            if (player.y > 650) 
            {
                inAir = true;
                player.y = 650;
            }

            //do camera correction
            if (player.x < WIDTH / 2) 
            {
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
        Platformer game = new Platformer();
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
        if (KeyCode == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (KeyCode == KeyEvent.VK_LEFT) {
            left = true;
        } else if (KeyCode == KeyEvent.VK_SPACE) {
            jump = true;
        } else if (KeyCode == KeyEvent.VK_Z) {
            powerDown = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (KeyCode == KeyEvent.VK_LEFT) {
            left = false;
        } else if (KeyCode == KeyEvent.VK_SPACE) {
            jump = false;
        } else if (KeyCode == KeyEvent.VK_Z) {
            powerDown = false;
        }
    }

    public void handleCollision(Rectangle player, Rectangle block) {
        //make overlap rectangle
        Rectangle overlap = player.intersection(block);
        dy = 0;
        if (player.y < block.y) 
        {
            player.y -= overlap.height;
            inAir = false;
        } else if (player.y + player.height > block.y + block.height) 
        {
            player.y += overlap.height;
        }
        

    }

    public void handleAttack(Rectangle player, Rectangle enemy) {
        //make overlap rectangle
        Rectangle overlap = player.intersection(block);

        dy = 0;
        if (player.y < block.y) 
        {
            player.y += overlap.height;
            enemy.y = -100;
            inAir = false;
        } else if (player.y + player.height > block.y + block.height) 
        {
            player.y += overlap.height;
        }
    }

}
