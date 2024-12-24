import java.awt.*;
import java.awt.image.BufferedImage;

public class StripePainter implements Runnable{

    private final Graphics g;
    private int start;
    private int width;
    private int height;
    private Color color;
    private BufferedImage img;

    public StripePainter(Graphics g, int start, int width, int height){
        this.g = g;
        this.start = start;
        this.width = width;
        this.height = height;
        color = new Color(
                (float)Math.random(),
                (float)Math.random(),
                (float)Math.random()
        );
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void run() {
        var g = img.createGraphics();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                g.setColor(color);
                g.fillRect(i, j, 1, 1);
            }
        }
        synchronized (this.g){
            this.g.drawImage(img, start, 0, null);
        }
    }
}
