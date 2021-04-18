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
            sorteBrikker.add(new Bonde(new Felt(6, x), false));
            hvideBrikker.add(new Bonde(new Felt(1, x), true));
            for(int y = 0; y < 8; ++y) {
                felter[x][y] = new Felt(x, y);
            }
        }
        sorteBrikker.add(new Springer(new Felt(7, 2), false));
        sorteBrikker.add(new Springer(new Felt(7, 5), false));
        sorteBrikker.add(new Loeber(new Felt(7, 1), false));
        sorteBrikker.add(new Loeber(new Felt(7, 6), false));

        hvideBrikker.add(new Springer(new Felt(0, 2), true));
        hvideBrikker.add(new Springer(new Felt(0, 5), true));
        hvideBrikker.add(new Loeber(new Felt(0, 1), true));
        hvideBrikker.add(new Loeber(new Felt(0, 6), true));
    }

    public Brik hentBrik(Felt felt) {
        for(Brik b: sorteBrikker) {
            if(b.felt().equals(felt)) {
                return b;
            }
        }
        for(Brik b: hvideBrikker) {
            if(b.felt().equals(felt)) {
                return b;
            }
        }
        return null;
    }

    public void fjernBrik(Felt felt) {
        Brik b = hentBrik(felt);
        if(b != null){
            sorteBrikker.remove(b);
            hvideBrikker.remove(b);
        }
    }

    public boolean kanLaveTraek(Traek traek) {
        for(Felt f: traek.brik.tilladteTraek(this)) {
            System.out.println(f.notation());
        }
        return traek.brik.tilladteTraek(this).contains(traek.til);
    }

    public void lavTraek(Traek traek) {
        if(!kanLaveTraek(traek)) {
            return;
        }
        fjernBrik(traek.til);
        traek.brik.flytTil(traek.til);
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
