package com.company;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import com.company.brikker.*;

public class Braet {
    Felt felter[][] = new Felt[8][8];
    LinkedList<Brik> sorteBrikker = new LinkedList<Brik>();
    Konge hvidKonge, sortKonge;
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
        sortKonge = new Konge(new Felt(7, 3), false);
        hvidKonge = new Konge(new Felt(0, 4), true);
        sorteBrikker.add(sortKonge);
        hvideBrikker.add(hvidKonge);

        sorteBrikker.add(new Springer(new Felt(7, 2), false));
        sorteBrikker.add(new Springer(new Felt(7, 5), false));
        sorteBrikker.add(new Loeber(new Felt(7, 1), false));
        sorteBrikker.add(new Loeber(new Felt(7, 6), false));
        sorteBrikker.add(new Taarn(new Felt(7, 0), false));
        sorteBrikker.add(new Taarn(new Felt(7, 7), false));
        sorteBrikker.add(new Dronning(new Felt(7, 4), false));

        hvideBrikker.add(new Springer(new Felt(0, 2), true));
        hvideBrikker.add(new Springer(new Felt(0, 5), true));
        hvideBrikker.add(new Loeber(new Felt(0, 1), true));
        hvideBrikker.add(new Loeber(new Felt(0, 6), true));
        hvideBrikker.add(new Taarn(new Felt(0, 0), true));
        hvideBrikker.add(new Taarn(new Felt(0, 7), true));
        hvideBrikker.add(new Dronning(new Felt(0, 3), true));
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

    public void lavTraek(Traek traek) {
        fjernBrik(traek.til);
        traek.brik.flytTil(traek.til);
    }

    public void fortrydTraek(Traek traek) {
        traek.brik.flytTil(traek.fra);
        if(traek.tagetBrik != null) {
            traek.tagetBrik.flytTil(traek.til);
            if(traek.tagetBrik.erHvid()) {
                hvideBrikker.add(traek.tagetBrik);
            }
            else {
                sorteBrikker.add(traek.tagetBrik);
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

    public boolean iSkak(boolean hvid) {
        if(hvid) {
            for(Brik b: sorteBrikker) {
                if(b.tilladteTraek(this).contains(hvidKonge.felt())) {
                    return true;
                }
            }
        }
        else {
            for(Brik b: hvideBrikker) {
                if(b.tilladteTraek(this).contains(sortKonge.felt())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pat(boolean hvid) {
        if(hvid) {
            for(Brik b: hvideBrikker) {
                if(b.tilladteTraek(this).size() == 0) {
                    return false;
                }
            }
        }
        else {
            for(Brik b: sorteBrikker) {
                if(b.tilladteTraek(this).size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean skakMat(boolean hvid) {
        if(!iSkak(hvid)) {
            return false;
        }
        LinkedList<Brik> brikker = null;
        if(hvid) {
            brikker = hvideBrikker;
        }
        else {
            brikker = sorteBrikker;
        }
        for(Brik b: brikker) {
            for(Felt til: b.tilladteTraek(this)) {
                Traek traek = new Traek(b.felt(), til, b, hentBrik(til));
                lavTraek(traek);
                if(!iSkak(hvid)) {
                    fortrydTraek(traek);
                    return false;
                }
                fortrydTraek(traek);
            }
        }
        return true;
    }

}
