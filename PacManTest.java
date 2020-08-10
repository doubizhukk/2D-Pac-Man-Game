package PacManApp;

import javax.swing.*;

public class PacManTest {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        PacMan pacman = new PacMan();
        frame.setTitle(pacman.TITLE);
        frame.add(pacman);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        pacman.start();


    }
}
