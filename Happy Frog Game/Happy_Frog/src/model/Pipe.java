/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import view.MainFrame;

/**
 *
 * @author Dang Phat
 */
public class Pipe implements ObjectSize {

    private final MainFrame mainFrame;    
    private JButton pipe1;
    private JButton pipe2;
    private int pipe_x;
    private int groove;
    private int pipe_height;
    private boolean checkPoint;

    public Pipe(MainFrame mainFrame, int groove, int pipe_height, int pipe_x) {
        this.mainFrame = mainFrame;
        pipe1 = new JButton();
        pipe2 = new JButton();
        this.groove = groove;
        this.pipe_height = pipe_height;
        this.pipe_x = pipe_x;
        checkPoint = false;
    }

    public int getPipe_x() {
        return pipe_x;
    }

    public void setPipe_x(int pipe_x) {
        this.pipe_x = pipe_x;
    }

    public int getGroove() {
        return groove;
    }

    public void setGroove(int groove) {
        this.groove = groove;
    }

    public int getPipe_height() {
        return pipe_height;
    }

    public void setPipe_height(int pipe_height) {
        this.pipe_height = pipe_height;
    }

    public boolean isCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }  

    public void addPipe() {
        pipe1.setBounds(pipe_x, 0, PIPE_WIDTH, pipe_height);
        pipe2.setBounds(pipe_x, pipe_height + groove, PIPE_WIDTH, mainFrame.getPnGame().getHeight() - pipe_height - groove);
        mainFrame.getPnGame().add(pipe1);
        mainFrame.getPnGame().add(pipe2);
        mainFrame.getPnGame().repaint();
    }
}
