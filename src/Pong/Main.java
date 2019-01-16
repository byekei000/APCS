package Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Canvas implements ActionListener, KeyListener {

    private static int width;
    private static int height;
    private Ball ball = new Ball();
    private Paddle paddle1 = new Paddle();
    private Paddle paddle2 = new Paddle();
    private int score1 = 0;
    private int score2 = 0;


    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        if(ball.xSpd == 0){
            ball.xSpd = 1;
            ball.ySpd = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            paddle2.dir = -1;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            paddle2.dir = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            paddle2.dir = -1;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            paddle2.dir = 1;
        }
    }
    public void keyReleased(KeyEvent e){
        paddle2.dir = 0;
    }

    private Main(int width, int height){
        this.width = width;
        this.height = height;
        ball.x = width/2-ball.width/2;
        ball.y = height/2-ball.height/2;
        paddle1.x = 50;
        paddle1.y = 200;
        paddle2.x = width-50;
        paddle2.y = 200;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer timer = new Timer(10,this);
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.black);
        g2d.fillRect(ball.x,ball.y,ball.width,ball.height);
        g2d.fillRect(paddle1.x, paddle1.y, paddle1.width, paddle1.height);
        g2d.fillRect(paddle2.x, paddle2.y, paddle2.width, paddle2.height);
        g2d.drawString(Integer.toString(score1), width-15,20);
        g2d.drawString(Integer.toString(score2), 10,20);
    }

    public void actionPerformed(ActionEvent e){
        if(ball.xDir == 1){
            ball.x += ball.xSpd;
        } else ball.x--;
        if(ball.yDir == 1){
            ball.y += ball.ySpd;
        } else ball.y--;
        if(ball.x < 0){
            ball.x = width/2;
            ball.y = height/2;
            score1 += 1;
        }
        if(ball.x+ball.width > width){
            ball.x = width/2;
            ball.y = height/2;
            score2 += 1;
        }
        if(ball.y < 0 || ball.y+ball.height > height){
            ball.yDir = -ball.yDir;
        }
        if(paddle2.dir == 1){
            paddle2.y -= 1;
        } else if(paddle2.dir == -1){
            paddle2.y += 1;
        }
        if(paddle1.dir == 1){
            paddle1.y -= 1;
        } else if(paddle1.dir == -1){
            paddle1.y += 1;
        }
        //Computer AI
        if(paddle1.y+paddle1.height/2-ball.height/2 < ball.y){
            paddle1.dir = -1;
        } else if(paddle1.y+paddle1.height/2-ball.height/2 > ball.y){
            paddle1.dir = 1;
        } else paddle1.dir = 0;

        if(paddle1.y < 0){
            paddle1.y = 0;
        } else if(paddle1.y > height-paddle2.height){
            paddle1.y = height-paddle1.height;
        }
        if(paddle2.y < 0){
            paddle2.y = 0;
        } else if(paddle2.y > height-paddle2.height){
            paddle2.y = height-paddle2.height;
        }

        if(new Rectangle(ball.x, ball.y, ball.width, ball.height).intersects(new Rectangle(paddle2.x, paddle2.y, paddle2.width, paddle2.height)) || new Rectangle(ball.x, ball.y, ball.width, ball.height).intersects(new Rectangle(paddle1.x, paddle1.y, paddle1.width, paddle1.height))){
            ball.xDir = -ball.xDir;
        }
        repaint();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        Canvas canvas = new Main(400,500);
        canvas.setSize(width, height);
        frame.setSize(width, height);
        frame.add(canvas);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}