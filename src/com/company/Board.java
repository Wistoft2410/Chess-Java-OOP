package com.company;

import javax.swing.*;
import java.awt.*;

public class Board {
    JFrame frame;
    JPanel[][] felt = new JPanel[8][8]; //Et felt

    int feltStørrelseX,feltStørrelseY;

    String morktFelt = "#658544"; //Pistache grøn farve
    String lystFelt = "#E6E8C6"; //Tan/Beige farve

    public Board(int tempFeltStørrelseX,int tempFeltStørrelseY) {
        frame = new JFrame("Skak");

        feltStørrelseX += tempFeltStørrelseX;
        feltStørrelseY += tempFeltStørrelseY;

        frame.setSize(feltStørrelseX,feltStørrelseX);
        frame.setLayout(new GridLayout(8, 8));
    }

    public void display(){

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                felt[i][j] = new JPanel();

                if ((i + j) % 2 == 0) {
                    felt[i][j].setBackground(Color.decode(lystFelt));
                } else {
                    felt[i][j].setBackground(Color.decode(morktFelt));
                }
                frame.add(felt[i][j]);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}