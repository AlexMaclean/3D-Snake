package snake;

import javax.swing.*;

public class Game extends JFrame {

    private Game() {
        Window window = new Window();
        add(window);
        setTitle("Cube");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
