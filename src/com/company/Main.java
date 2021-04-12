package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Brik> br = new LinkedList<>();
        Board board = new Board(500,500);
        board.display();
    }
}