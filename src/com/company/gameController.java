package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;



public class gameController {
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

    //datastrukturen der indeholder brikkerne skal være global og dermed tilgængelig for i alle GameController scopes
    public static LinkedList<Brik> bb = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        /*BrikImport brikker = new BrikImport();
        brikker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        brikker.setVisible(true);
        brikker.pack();
        brikker.setTitle("Image Program");*/


        //orginaltBrikBillede importeres så de enkelte brikker kan blive skåret ud
        BufferedImage orginaltBrikBillede = ImageIO.read(new File("/Users/computer/Documents/GitHub/Chess-Java-OOP/data/brikker.png"));

        Image brikker[] = new Image[12];

        int isoleretBrik = 0;

        for(int y =0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                brikker[isoleretBrik] = orginaltBrikBillede.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                isoleretBrik++;
            }
        }

        //test brik
        Brik hkonge = new Brik(0,0, true,"konge", bb);

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
                for (Brik brikBillede: bb) {

                    int isoleretBrik = 0;

                    if (brikBillede.type.equalsIgnoreCase("konge")) {
                        isoleretBrik = 0;
                    }

                    if (brikBillede.type.equalsIgnoreCase("dronning")) {
                        isoleretBrik = 1;
                    }

                    if (brikBillede.type.equalsIgnoreCase("løber")) {
                        isoleretBrik = 2;
                    }

                    if (brikBillede.type.equalsIgnoreCase("springer")) {
                        isoleretBrik = 3;
                    }

                    if (brikBillede.type.equalsIgnoreCase("taarn")) {
                        isoleretBrik = 4;
                    }

                    if (brikBillede.type.equalsIgnoreCase("bonde")) {
                        isoleretBrik = 5;
                    }

                    if (!brikBillede.isWhite) {
                        isoleretBrik += 6; //rykker 6 frem for at skære de ikke-hvide brikker ud (!brikBillede.isWhite)
                    }
                    g.drawImage(brikker[isoleretBrik], brikBillede.xp, brikBillede.yp,this);
                }
            }
        };

        frame.add(board);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}