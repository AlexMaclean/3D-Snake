package snake;


import java.awt.Color;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
public class Game extends JFrame {
    
    Window window = new Window();

   public Game() {
        add(window);
        setTitle("Cube phone");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(true);
        setResizable(false);
        
    }
 
   
   public static void main(String[] args) {
       new Game();
    }
    
}
