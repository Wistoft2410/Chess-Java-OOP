package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Main {
    /*
    public static class BrikImport extends JFrame {
        private ImageIcon image1;
        private JLabel label1;

        public BrikImport(){
            setLayout(new FlowLayout());

            image1 = new ImageIcon(getClass().getResource("brikker.png"));

            label1 = new JLabel(image1);
            add(label1);

        }
    }

     */


    public static void main(String[] args){
        /*BrikImport brikker = new BrikImport();
        brikker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        brikker.setVisible(true);
        brikker.pack();
        brikker.setTitle("Image Program");*/


        LinkedList<Brik> br = new LinkedList<>();
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel board = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                boolean white = true;

                //Her generer jeg skakbrættet
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(0)); //mørke felter: g.setColor(new Color(235,235,208));
                        } else {
                            g.setColor(new Color(255, 255, 255)); //lyse felter: g.setColor(new Color(119,148,85));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
            }
        };
        frame.add(board);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}