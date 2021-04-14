package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.MainFrame;

/**
 *
 * @author dangphat
 */
public class Frog implements ObjectSize{

    private final MainFrame mainFrame;
    public JButton frog;
    public int frog_x;
    public int frog_y;    
    public float vy;

    public Frog(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        frog_x = mainFrame.getWidth() / 2 - FROG_WIDTH;
        frog_y = mainFrame.getHeight() / 2 - 3 *FROG_HEIGHT;
        ImageIcon image = new ImageIcon("src/img/frog.png");
        frog = new JButton();
        vy = 0;
    }

    public int getFrog_x() {
        return frog_x;
    }

    public int getFrog_y() {
        return frog_y;
    }

    public float getVy() {
        return vy;
    }

    public void setFrog_x(int frog_x) {
        this.frog_x = frog_x;
    }

    public void setFrog_y(int frog_y) {
        this.frog_y = frog_y;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }   
    
    public void frogFall() {
        frog_y += vy;
        vy += 0.5;
    }

    public void frogJump() {
        vy = - FROG_JUMP;
    }

    public void reset() {
        frog_x = mainFrame.getWidth() / 2 - FROG_WIDTH;
        frog_y = mainFrame.getHeight() / 2 - 3 * FROG_HEIGHT;
        vy = 0;
    }

    public void renderFrog() {
        frog.setBounds(frog_x, frog_y, FROG_WIDTH, FROG_HEIGHT);
        //defind limit of frog 
        mainFrame.getPnGame().add(frog);
    }

}
