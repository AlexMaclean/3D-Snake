package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Cube implements Comparable {
    private ImageIcon icon = new ImageIcon(getClass().getResource("images/cube.png"));
    private int x, y, z;
    int CountDown;

    public Cube(int x, int y, int z, int countdown) {
        this.x = x;
        this.y = y;
        this.z = z;
        CountDown = countdown;
    }

    public void tick() {
        CountDown--;
    }

    public void Draw(Graphics2D g2d, int rotation) {
        int realX = 0;
        int realY = 0;
        switch (rotation) {

            case 1:
                realX = realX - 14 * x;
                realY = realY - 7 * x;
                realX = realX + 14 * y;
                realY = realY - 7 * y;
                break;

            case 2:
                realX = realX + 14 * x;
                realY = realY - 7 * x;
                realX = realX + 14 * y;
                realY = realY + 7 * y;
                break;

            case 3:
                realX = realX + 14 * x;
                realY = realY + 7 * x;
                realX = realX - 14 * y;
                realY = realY + 7 * y;
                break;

            case 4:
                realX = realX - 14 * x;
                realY = realY + 7 * x;
                realX = realX - 14 * y;
                realY = realY - 7 * y;
                break;
        }
        realY = realY + 14 * z;
        Image img = icon.getImage();
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        changeColor(bimage, CountDown);
        g2d.drawImage(bimage, 385 - realX, 385 - realY, null);
    }

    private void changeColor(BufferedImage img, int change) {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                final int oldRGB = img.getRGB(x, y);
                Color c = new Color(oldRGB, true);
                Color cNew = new Color(Math.min(255, change / 5), Math.min(255, c.getGreen()), Math.min(255, c.getBlue()), c.getAlpha());
                final int newRGB = cNew.getRGB();
                img.setRGB(x, y, newRGB);
            }
        }
    }

    private int getLayer(int rotation) {
        switch (rotation) {

            case 1:
                return x + y + z;
            case 2:
                return x - y + z;
            case 3:
                return -x - y + z;
            case 4:
                return -x + y + z;
        }
        return 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public int compareTo(Object arg0) {
        Cube comparingTo = (Cube) arg0;
        return getLayer(Window.Rotation) - comparingTo.getLayer(Window.Rotation);
    }

    @Override
    public boolean equals(Object other) {
        Cube comparingTo = (Cube) other;
        return comparingTo.getX() == x &&
                comparingTo.getY() == y &&
                comparingTo.getZ() == z;
    }
}
