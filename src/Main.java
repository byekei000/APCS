import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Canvas implements ActionListener {

    private static int width;
    private static int height;
    private int x = 0;
    private int xdir = 1;
    private int y = 0;
    private int ydir = 1;

    private Main(int width, int height){
        this.width = width;
        this.height = height;
        Timer timer = new Timer(10,this);
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.black);
        g2d.fillRect(x,y,100,100);
    }

    public void actionPerformed(ActionEvent e){
        if(xdir == 1){
            x++;
        } else x--;
        if(ydir == 1){
            y++;
        } else y--;
        if(x < 0 || x+100 > width){
            xdir = -xdir;
        }
        if(y < 0 || y+100 > height){
            ydir = -ydir;
        }
        repaint();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        Canvas canvas = new Main(500,500);
        canvas.setSize(width, height);
        frame.setSize(width, height);
        frame.add(canvas);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}