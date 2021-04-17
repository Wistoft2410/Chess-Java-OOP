package com.company;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import com.company.brikker.*;

public class Braet {
    Felt felter[][] = new Felt[8][8];
    LinkedList<Brik> sorteBrikker = new LinkedList<Brik>();
    LinkedList<Brik> hvideBrikker = new LinkedList<Brik>();

    public Braet() {
        //Her generer jeg skakBraettet
        for(int x = 0; x < 8; ++x) {
            sorteBrikker.add(new Bonde(new Felt(1, x), false));
            hvideBrikker.add(new Bonde(new Felt(6, x), true));
            for(int y = 0; y < 8; ++y) {
                felter[x][y] = new Felt(x, y);
            }
        }
    }

    public void display(Graphics g, ImageObserver observer) {
        // Tegner underlæggende felter
        for(Felt[] række: felter) {
            for(Felt f: række) {
                f.display(g);
            }
        }
        // Tegner brikker
        for(Brik b: sorteBrikker) {
            b.display(g, observer);
        }
        for(Brik b: hvideBrikker) {
            b.display(g, observer);
        }
    }
}
