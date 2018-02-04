package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class Window extends JPanel implements KeyListener {

    private static final int X_BOUND = 12;
    private static final int Y_BOUND = 12;
    private static final int Z_BOUND = 12;
    public static int Rotation = 1;
    private ArrayList<Cube> Cubes = new ArrayList<>();
    private int randMove = 10;
    private int MaxrandMove = 10;
    private Cube up, down, forward, backward, left, right;

    private Cube Direction;
    private Cube Head;

    Window() {
        up = new Cube(0, 0, 1, 1);
        down = new Cube(0, 0, -1, 1);
        forward = new Cube(1, 0, 0, 1);
        backward = new Cube(-1, 0, 0, 1);
        right = new Cube(0, 1, 0, 1);
        left = new Cube(0, -1, 0, 1);
        Direction = new Cube(0, 0, 1, 1);
        Head = new Cube(0, 0, 0, 1);

        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(this);
        Timer _timer = new Timer(5, e -> paintInterval());
        _timer.start();
    }

    private void paintInterval() {
        randMove--;
        ArrayList<Cube> newCubes = new ArrayList<>();
        int newX = Direction.getX() + Head.getX();
        int newY = Direction.getY() + Head.getY();
        int newZ = Direction.getZ() + Head.getZ();
        boolean intersects = false;
        double length = 1300;
        Cube newHead = new Cube(newX, newY, newZ, (int) length);
        for (Cube cube2 : Cubes) {
            if (newHead.equals(cube2))
                intersects = true;
        }
        if (newX < X_BOUND && newX > -X_BOUND && newY < Y_BOUND && newY > -Y_BOUND && newZ < Z_BOUND && newZ > -Z_BOUND && !intersects) {
            Cubes.add(Head = newHead);
        } else {
            int tries = 0;
            while ((newX >= X_BOUND || newX <= -X_BOUND || newY >= Y_BOUND || newY <= -Y_BOUND || newZ >= Z_BOUND || newZ <= -Z_BOUND) || (tries < 20 && intersects)) {
                int random = (int) (1 + Math.random() * 4);
                if (newX >= X_BOUND || newX <= -X_BOUND) {
                    switch (random) {

                        case 1:
                            Direction = up;
                            break;
                        case 2:
                            Direction = down;
                            break;
                        case 3:
                            Direction = right;
                            break;
                        case 4:
                            Direction = left;
                            break;
                    }
                }
                if (newY >= Y_BOUND || newY <= -Y_BOUND) {
                    switch (random) {

                        case 1:
                            Direction = up;
                            break;
                        case 2:
                            Direction = down;
                            break;
                        case 3:
                            Direction = forward;
                            break;
                        case 4:
                            Direction = backward;
                            break;
                    }
                }
                if (newZ >= Z_BOUND || newZ <= -Z_BOUND) {
                    switch (random) {

                        case 1:
                            Direction = backward;
                            break;
                        case 2:
                            Direction = forward;
                            break;
                        case 3:
                            Direction = right;
                            break;
                        case 4:
                            Direction = left;
                            break;
                    }
                }
                if (intersects) {
                    int random3 = (int) (1 + Math.random() * 6);
                    tries++;
                    switch (random3) {
                        case 1:
                            Direction = up;
                            break;
                        case 2:
                            Direction = down;
                            break;
                        case 3:
                            Direction = right;
                            break;
                        case 4:
                            Direction = left;
                            break;
                        case 5:
                            Direction = forward;
                            break;
                        case 6:
                            Direction = backward;
                            break;
                    }
                }
                newX = Direction.getX() + Head.getX();
                newY = Direction.getY() + Head.getY();
                newZ = Direction.getZ() + Head.getZ();
                intersects = false;
                newHead = new Cube(newX, newY, newZ, (int) length);
                for (Cube cube2 : Cubes) {
                    if (newHead.equals(cube2))
                        intersects = true;

                }
            }
            Cubes.add(Head = newHead);

        }
        for (Cube cube : Cubes) {

            cube.tick();
            if (cube.CountDown != 0) {
                newCubes.add(cube);
            }
        }
        Cubes = newCubes;

        Collections.sort(Cubes);
        if (randMove == 0) {
            randMove = MaxrandMove;
            int random2 = (int) (1 + Math.random() * 6);

            switch (random2) {
                case 1:
                    Direction = up;
                    break;
                case 2:
                    Direction = down;
                    break;
                case 3:
                    Direction = right;
                    break;
                case 4:
                    Direction = left;
                    break;
                case 5:
                    Direction = forward;
                    break;
                case 6:
                    Direction = backward;
                    break;
            }
        }
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Cube cube : Cubes) {
            cube.Draw(g2d, Rotation);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                MaxrandMove = Math.max(1, MaxrandMove - 1);
                break;

            case KeyEvent.VK_DOWN:
                MaxrandMove = Math.min(100, MaxrandMove + 1);
                break;

            case KeyEvent.VK_LEFT:
                Rotation++;
                if (Rotation == 5) Rotation = 1;
                Collections.sort(Cubes);
                break;

            case KeyEvent.VK_RIGHT:
                Rotation--;
                if (Rotation == 0) Rotation = 4;
                Collections.sort(Cubes);
                break;

            case KeyEvent.VK_BACK_SPACE:
                Head = new Cube(0, 0, 0, 1);
                Cubes.clear();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
