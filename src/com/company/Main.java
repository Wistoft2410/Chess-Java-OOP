package com.company;

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

    public static void main(String[] args) {
        /*BrikImport brikker = new BrikImport();
        brikker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        brikker.setVisible(true);
        brikker.pack();
        brikker.setTitle("Image Program");*/ 

        LinkedList<Brik> br = new LinkedList<>();
        Board board = new Board(500,500);
        board.display();
    }

}