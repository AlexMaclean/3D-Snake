package snake;


import javax.swing.JFrame;
 
public class Game extends JFrame {
    
    Window window = new Window();

   public Game() {
        add(window);
        setTitle("Cube");
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
