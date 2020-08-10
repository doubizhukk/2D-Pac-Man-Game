package PacManApp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Apple extends Rectangle {

    private static final long serialVersionUID = 1L;
    private BufferedImage foodImage;

    public Apple(int x, int y){
        setBounds(x+10, y+10, 20, 20);
        try{
            foodImage = ImageIO.read(getClass().getResource("apple.gif"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public void render(Graphics g){
        g.drawImage(foodImage, x, y, width, height, null);
    }
}
