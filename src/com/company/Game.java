package com.company;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Game {
    public static LinkedList<Brik> bb = new LinkedList<>();

    public Game() throws IOException {


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

        //Bag back row
        Brik brook1 = new Brik(0,0, false,"taarn", bb);
        Brik bknight1 = new Brik(1,0, false,"springer", bb);
        Brik bbishop1 = new Brik(2,0, false,"lober", bb);
        Brik bqueen = new Brik(3,0, false,"dronning", bb);
        Brik bking = new Brik(4,0, false,"konge", bb);
        Brik bbishop2 = new Brik(5,0, false,"lober", bb);
        Brik bknight2 = new Brik(6,0, false,"springer", bb);
        Brik brook2 = new Brik(7,0, false,"taarn", bb);

        //Black front row
        Brik bpawn = new Brik(0,1, false,"bonde", bb);
        Brik bpawn1 = new Brik(1,1, false,"bonde", bb);
        Brik bpawn2 = new Brik(2,1, false,"bonde", bb);
        Brik bpawn3 = new Brik(3,1, false,"bonde", bb);
        Brik bpawn4 = new Brik(4,1, false,"bonde", bb);
        Brik bpawn5 = new Brik(5,1, false,"bonde", bb);
        Brik bpawn6 = new Brik(6,1, false,"bonde", bb);
        Brik bpawn7 = new Brik(7,1, false,"bonde", bb);

        //White back row
        Brik wrook1 = new Brik(0,7, true,"taarn", bb);
        Brik wknight1 = new Brik(1,7, true,"springer", bb);
        Brik wbishop1 = new Brik(2,7, true,"lober", bb);
        Brik wqueen = new Brik(3,7, true,"dronning", bb);
        Brik wking = new Brik(4,7, true,"konge", bb);
        Brik wbishop2 = new Brik(5,7, true,"lober", bb);
        Brik wknight2 = new Brik(6,7, true,"springer", bb);
        Brik wrook2 = new Brik(7,7, true,"taarn", bb);

        //White front row
        Brik hbonde1 = new Brik(0,6, true,"bonde", bb);
        Brik hbonde2 = new Brik(1,6, true,"bonde", bb);
        Brik hbonde3 = new Brik(2,6, true,"bonde", bb);
        Brik hbonde4 = new Brik(3,6, true,"bonde", bb);
        Brik hbonde5 = new Brik(4,6, true,"bonde", bb);
        Brik hbonde6 = new Brik(5,6, true,"bonde", bb);
        Brik hbonde7 = new Brik(6,6, true,"bonde", bb);
        Brik hbonde8 = new Brik(7,6, true,"bonde", bb);

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

                    if (brikBillede.type.equalsIgnoreCase("lober")) {
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