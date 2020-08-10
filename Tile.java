package PacManApp;

import javax.imageio.ImageIO;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile extends Rectangle {

    private static final long serialVersionUID = 1L;
    private BufferedImage wellImage;

    public Tile(int x, int y){

        setBounds(x, y, 32, 32);
        try{
            wellImage = ImageIO.read(getClass().getResource("wall.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void render(Graphics g){
        //g.setColor(new Color(33, 0, 127));
        g.drawImage(wellImage, x, y, width, height, null);

    }
}
