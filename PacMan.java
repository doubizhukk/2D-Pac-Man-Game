package PacManApp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImageFilter;

public class PacMan extends Canvas implements Runnable, KeyListener {

    private boolean isRunning = false;
    public static final int WIDTH = 640, HEIGHT = 480;
    public static final String TITLE = "Pac-Man";

    private Thread thread;
    public static Player player;
    public static Level level;
    //public static SpriteSheet spriteSheet;

    public PacMan(){
        Dimension d = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        player = new Player(WIDTH/2, HEIGHT/2);
        level = new Level("map.png");

    }

    public synchronized void start(){

        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop(){

        if(!isRunning) return;
        isRunning = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void tick(){
        player.tick();
        level.tick();
    }

    private void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        level.render(g);
        player.render(g);
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        requestFocus();
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        double ns = 1000000000/targetTick;

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                render();
                fps++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }

        }
        stop();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
            player.dir = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = true;
            player.dir = 1;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
            player.dir = 2;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.down = true;
            player.dir = 3;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            player.right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            player.left = false;
        if(e.getKeyCode() == KeyEvent.VK_UP)
            player.up = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            player.down = false;
    }
}
