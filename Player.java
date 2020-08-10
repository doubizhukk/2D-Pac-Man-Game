package PacManApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle {

    private static final long serialVersionUID = 1L;
    public boolean right, left, up, down;
    public int dir = -1, rightDir = 0, leftDir = 1, upDir = 2, downDir = 3;
    private int speed = 2;
    private BufferedImage PacManLeftImage;
    private BufferedImage PacManRightImage;
    private BufferedImage PacManUpImage;
    private BufferedImage PacManDownImage;

    public Player(int x, int y){
        setBounds(x, y, 20, 20);

        try{
            PacManLeftImage = ImageIO.read(getClass().getResource("leftmouth.png"));
            PacManRightImage = ImageIO.read(getClass().getResource("rightmouth.png"));
            PacManUpImage = ImageIO.read(getClass().getResource("upmouth.png"));
            PacManDownImage = ImageIO.read(getClass().getResource("downmouth.png"));
        }catch(IOException e){e.printStackTrace();}
    }

    public void tick(){
        if(right && canMove(x+speed, y)) x += speed;
        if(left && canMove(x-speed, y)) x -= speed;
        if(up && canMove(x, y-speed)) y -= speed;
        if(down && canMove(x, y+speed)) y += speed;

        Level level = PacMan.level;

        for(int i = 0; i < level.apples.size(); i++){
            if(this.intersects(level.apples.get(i))){
                level.apples.remove(i);
                break;
            }
        }
        if(level.apples.size() == 0){
            //Game end, we win!
            PacMan.player = new Player(32, 32);
            PacMan.level = new Level("map.png");
            return;
            }
        for(int i = 0; i < PacMan.level.enemies.size(); i++){
            Enemy en = PacMan.level.enemies.get(i);
            if(en.intersects(this)){
                //game over
                System.exit(1);
            }
        }
    }

    private boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx, nexty, width, height);
        Level level = PacMan.level;

        for(int xx = 0; xx < level.tiles.length; xx++){
            for(int yy = 0; yy < level.tiles[0].length; yy++){
                if(level.tiles[xx][yy] != null){
                    if(bounds.intersects(level.tiles[xx][yy])){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void render(Graphics g){
        if(dir == rightDir) g.drawImage(PacManRightImage, x, y, width, height, null);
        else if(dir == leftDir) g.drawImage(PacManLeftImage, x, y, width, height, null);
        else if(dir == upDir) g.drawImage(PacManUpImage, x, y, width, height, null);
        else if(dir == downDir) g.drawImage(PacManDownImage, x, y, width, height, null);
        else g.drawImage(PacManRightImage, x, y, width, height, null);
    }

}
