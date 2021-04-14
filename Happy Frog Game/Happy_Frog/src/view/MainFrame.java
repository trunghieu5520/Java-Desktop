/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Frog;
import model.ObjectSize;
import static model.ObjectSize.GROOVE_MAX;
import static model.ObjectSize.GROOVE_MIN;
import model.Pipe;

/**
 *
 * @author Dang Phat
 */
public class MainFrame extends javax.swing.JFrame implements ObjectSize {

    /**
     * Creates new form MainFrame
     */
    private Frog frog;
    private ArrayList<Pipe> listPipe;
    private ArrayList<Pipe> toRemove;
    private int groove;
    private int pipe_height;
    private int pipe_x;
    private int groove_rd;
    private int pipe_height_rd;
    private int distance_rd;
    private int speed;
    private int point = 0;
    private Timer timer;
    private boolean isRelease = true;
    private boolean isPress = false;

    public MainFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        frog = new Frog(this);
        listPipe = new ArrayList<>();
        toRemove = new ArrayList<>();
        //check if file exists then ask user want to play save game or not
        File f = new File("src/model/data.txt");
        if (f.exists()) {
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play save game?");
            switch (choice) {
                case 0: {
                    loadData();
                    playGame();
                }
                break;
                case 1: {
                    newGame();
                    playGame();
                }
                break;
                default:
                    System.exit(0);
            }
        } else {
            newGame();
        }
        addEvents();
        pack();
    }

    public void playGame() {
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set speed, groove and distance for each level
                if (point < 10) {
                    speed = 2;
                    groove_rd = GROOVE_MAX - GROOVE_MIN;
                    distance_rd = pnGame.getWidth() / 2;
                } else if (point < 20) {
                    speed = 3;
                    groove_rd = (GROOVE_MAX - GROOVE_MIN) * 3 / 4;
                    distance_rd = pnGame.getWidth() / 2 * 3 / 4;
                } else if (point < 30) {
                    speed = 4;
                    groove_rd = (GROOVE_MAX - GROOVE_MIN) * 1 / 2;
                    distance_rd = pnGame.getWidth() / 2 * 1 / 2;
                } else {
                    speed = 5;
                    groove_rd = 1;
                    distance_rd = 1;
                }
                //frog falls
                frog.frogFall();
                //if press UP then frog will jump
                if (isPress) {
                    frog.frogJump();
                    isPress = false;
                }
                //if the last pipe equals pnGame's width then add new pipe
                if (!listPipe.isEmpty() && listPipe.get(listPipe.size() - 1).getPipe_x() <= pnGame.getWidth()) {
                    addPipe();
                }
                if (checkTouch()) {
                    timer.stop();
                    showMess();
                }
                //remove pipes
                listPipe.removeAll(toRemove);
                //render
                render();
            }
        });
        timer.start();
    }

    public void newGame() {
        frog = new Frog(this);
        listPipe = new ArrayList<>();
        toRemove = new ArrayList<>();
        speed = 2;
        point = 0;
        lblPoint.setText(point + "");
        if (listPipe.isEmpty()) {
            pipe_x = pnGame.getWidth();
            groove_rd = GROOVE_MAX - GROOVE_MIN;
            groove = new Random().nextInt(groove_rd) + GROOVE_MIN;
            pipe_height_rd = pnGame.getHeight() - 2 * PIPE_HEIGHT_MIN - groove;
            pipe_height = PIPE_HEIGHT_MIN + new Random().nextInt(pipe_height_rd);
            Pipe pipe = new Pipe(this, groove, pipe_height, pipe_x);
            listPipe.add(pipe);
        }
    }

    public void addPipe() {
        pipe_x = pnGame.getWidth() + new Random().nextInt(distance_rd) + pnGame.getWidth() / 8 + PIPE_WIDTH;
        groove = new Random().nextInt(groove_rd) + GROOVE_MIN;
        pipe_height_rd = pnGame.getHeight() - 2 * PIPE_HEIGHT_MIN - groove;
        pipe_height = PIPE_HEIGHT_MIN + new Random().nextInt(pipe_height_rd);
        Pipe pipe = new Pipe(this, groove, pipe_height, pipe_x);
        listPipe.add(pipe);
    }

    public void loadData() {
        File f = new File("src/model/data.txt");
        listPipe = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = "";
            int countLine = 0;
            while ((line = br.readLine()) != null) {
                countLine++;
                String[] s = line.split(";");
                if (countLine == 1) {
                    point = Integer.parseInt(s[0]);
                    lblPoint.setText(point + "");
                    frog.setFrog_x(Integer.parseInt(s[1]));
                    frog.setFrog_y(Integer.parseInt(s[2]));
                    frog.setVy(Float.parseFloat(s[3]));
                } else {
                    pipe_x = Integer.parseInt(s[0]);
                    pipe_height = Integer.parseInt(s[1]);
                    groove = Integer.parseInt(s[2]);
                    if (pipe_x >= 0 - PIPE_WIDTH && pipe_x <= pnGame.getWidth() + pnGame.getWidth() / 2 + pnGame.getWidth() / 8 + PIPE_WIDTH
                            && pipe_height >= PIPE_HEIGHT_MIN && pipe_height <= PIPE_HEIGHT_MIN + pnGame.getHeight() - 2 * PIPE_HEIGHT_MIN - groove
                            && groove >= GROOVE_MIN && groove <= GROOVE_MAX) {
                        Pipe pipe = new Pipe(this, groove, pipe_height, pipe_x);
                        pipe.setCheckPoint(Boolean.valueOf(s[3]));
                        listPipe.add(pipe);
                    } else {
                        if (f.delete()) {
                            messLoadFileFail();
                        }
                    }
                }
            }

        } catch (IOException | NumberFormatException ex) {
//           JOptionPane.showConfirmDialog(null,"Game is crash ?\n Do you want to play new game ?");
//           newGame();
            if (f.delete()) {
                messLoadFileFail();
            }
        }
    }
       

    public void addEvents() {
        btnPause.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (isRelease) {
                        isPress = true;
                        isRelease = false;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                isRelease = true;
            }
        });
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnPause.getText().equalsIgnoreCase("pause")) {
                    timer.stop();
                    btnPause.setText("Resume");
                } else {
                    timer.start();
                    btnPause.setText("Pause");
                }
            }
        });
        btnPause.setFocusable(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               saveGame();
                           
            }
        });
    }

    public void render() {
        pnGame.removeAll();
        frog.renderFrog();
        for (Pipe pipe : listPipe) {
            //add a couple pipes to pnGame
            pipe.addPipe();
            //check point
            if (pipe.getPipe_x() <= frog.getFrog_x()) {
                if (!pipe.isCheckPoint()) {
                    lblPoint.setText(++point + "");
                    pipe.setCheckPoint(true);
                }
            }
            //if a couple pipes's pipe_x is less than 0 - PIPE_WIDTH then remove 
            if (pipe.getPipe_x() <= 0 - PIPE_WIDTH) {
                toRemove.add(pipe);
            }
            //move
            pipe.setPipe_x(pipe.getPipe_x() - speed);
        }
        pnGame.repaint();
    }

    public boolean checkTouch() {
        if (frog.getFrog_y() <= 0 || frog.getFrog_y() >= pnGame.getHeight() - FROG_HEIGHT) {
            return true;
        }
        Rectangle frog_rect = new Rectangle(frog.getFrog_x(), frog.getFrog_y(), FROG_WIDTH, FROG_HEIGHT);
        for (Pipe p : listPipe) {
            Rectangle pipe1_rect = new Rectangle(p.getPipe_x(), 0, PIPE_WIDTH, p.getPipe_height());
            Rectangle pipe2_rect = new Rectangle(p.getPipe_x(), p.getPipe_height() + p.getGroove(), PIPE_WIDTH, pnGame.getHeight() - p.getPipe_height() - p.getGroove());
            if (frog_rect.intersects(pipe1_rect) || frog_rect.intersects(pipe2_rect)) {
                return true;
            }
        }
        return false;
    }

    private void showMess() {
        String mess = "";
        if (point < 10) {
            mess = "You got no medal!";
        } else if (point < 20) {
            mess = "Congatulation! You got bronze medal!";
        } else if (point < 30) {
            mess = "Congatulation! You got silver medal!";
        } else {
            mess = "Congatulation! You got gold medal!";
        }
        mess += "\nDo you want to play saved game?";
        int choice = JOptionPane.showConfirmDialog(null, mess);
        switch (choice) {
            case 0: {
                loadData();
                playGame();
            }
            break;
            case 1: {
                newGame();
                playGame();
            }
            break;
            default:
                System.exit(0);
        }
    }

    public void saveGame() {
        File f = new File("src/model/data.txt");
        try {
            PrintWriter pt = new PrintWriter(f);
            pt.println(point + ";" + frog.getFrog_x() + ";" + frog.getFrog_y() + ";" + frog.getVy());
            for (Pipe pipe : listPipe) {
                pt.println(pipe.getPipe_x() + ";" + pipe.getPipe_height() + ";" + pipe.getGroove() + ";" + pipe.isCheckPoint());
            }
            pt.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        isPress= true;
    }

    public void messLoadFileFail() {
        int choice = JOptionPane.showConfirmDialog(null, "Data file is error!\nDo you want to play new game?");
        switch (choice) {
            case 0: {
                newGame();
                playGame();
            }
            break;
            default:
                System.exit(0);
        }
    }

    public JPanel getPnGame() {
        return pnGame;
    }

    public void setPnGame(JPanel pnGame) {
        this.pnGame = pnGame;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnGame = new javax.swing.JPanel();
        btnPause = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblPoint = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnGame.setBackground(new java.awt.Color(204, 204, 204));
        pnGame.setBorder(new javax.swing.border.MatteBorder(null));
        pnGame.setForeground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout pnGameLayout = new javax.swing.GroupLayout(pnGame);
        pnGame.setLayout(pnGameLayout);
        pnGameLayout.setHorizontalGroup(
            pnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnGameLayout.setVerticalGroup(
            pnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );

        btnPause.setText("Pause");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Point(s):");

        lblPoint.setText("0");

        btnExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPause)
                        .addGap(66, 66, 66)
                        .addComponent(btnSave)
                        .addGap(128, 128, 128)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 704, Short.MAX_VALUE)
                        .addComponent(btnExit)
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPause)
                    .addComponent(btnSave)
                    .addComponent(jLabel1)
                    .addComponent(lblPoint)
                    .addComponent(btnExit))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPoint;
    private javax.swing.JPanel pnGame;
    // End of variables declaration//GEN-END:variables
}
