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
        // Her generes alle brikker
        sortKonge = new Konge(new Felt(7, 4), false);
        hvidKonge = new Konge(new Felt(0, 4), true);
        sorteBrikker.add(sortKonge);
        hvideBrikker.add(hvidKonge);

        sorteBrikker.add(new Springer(new Felt(7, 2), false));
        sorteBrikker.add(new Springer(new Felt(7, 5), false));
        sorteBrikker.add(new Loeber(new Felt(7, 1), false));
        sorteBrikker.add(new Loeber(new Felt(7, 6), false));
        sorteBrikker.add(new Taarn(new Felt(7, 0), false));
        sorteBrikker.add(new Taarn(new Felt(7, 7), false));
        sorteBrikker.add(new Dronning(new Felt(7, 3), false));

        hvideBrikker.add(new Springer(new Felt(0, 2), true));
        hvideBrikker.add(new Springer(new Felt(0, 5), true));
        hvideBrikker.add(new Loeber(new Felt(0, 1), true));
        hvideBrikker.add(new Loeber(new Felt(0, 6), true));
        hvideBrikker.add(new Taarn(new Felt(0, 0), true));
        hvideBrikker.add(new Taarn(new Felt(0, 7), true));
        hvideBrikker.add(new Dronning(new Felt(0, 3), true));
    }

    // Finder brikken på feltet
    public Brik hentBrik(Felt felt) {
        // Tjekker alle sorte brikker igennem
        for(Brik b: sorteBrikker) {
            if(b.felt().equals(felt)) {
                return b;
            }
        }
        // Tjekker alle hvide brikker igennem
        for(Brik b: hvideBrikker) {
            if(b.felt().equals(felt)) {
                return b;
            }
        }
        return null;
    }

    // Fjerner brikken på feltet
    public void fjernBrik(Felt felt) {
        // Først findes brikken
        Brik b = hentBrik(felt);
        // Derefter fjernes den fra listen
        if(b != null){
            sorteBrikker.remove(b);
            hvideBrikker.remove(b);
        }
    }

    // Udføre et træk ved først at fjerne den fangede brik og derefter rykke brikken som bliver rykket
    public void lavTraek(Traek traek) {
        if(traek.tagetBrik != null){
            sorteBrikker.remove(traek.tagetBrik);
            hvideBrikker.remove(traek.tagetBrik);
        }
        traek.brik.flytTil(traek.til);
    }

    // Fortryder et træk ved først at flytte brikken tilbage, også tilføje den fangede brik til brikkerne i dens egen farve.
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

    public void displayFelter(Graphics g) {
        for(Felt[] række: felter) {
            for(Felt f: række) {
                f.display(g);
            }
        }
    }
    public void displayBrikker(Graphics g, ImageObserver observer) {
        for(Brik b: sorteBrikker) {
            b.display(g, observer);
        }
        for(Brik b: hvideBrikker) {
            b.display(g, observer);
        }
    }

    // Ser om der er skak imod spilleren
    // Dette bliver gjort ved at gå igennem alle modstanderes tilladte træk og se om de indeholder kongens felt
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

    // Ser om der er pat imod spilleren.
    public boolean pat(boolean hvid) {
        return false;
    }

    // Ser om der er skak mat imod spilleren.
    // Dette gøres ved at først se om der er skak imod spilleren,
    // og dernest prøve alle tilladte træk for at se om man kan komme ud af skak
    public boolean skakMat(boolean hvid) {
        if(!iSkak(hvid)) {
            return false;
        }
        // Finder brikkerne som spilleren ejer
        LinkedList<Brik> brikker = null;
        if(hvid) {
            brikker = hvideBrikker;
        }
        else {
            brikker = sorteBrikker;
        }
        // Går igennem alle brikker
        for(Brik b: brikker) {
            // Går igennem alle træk for brik
            for(Felt til: b.tilladteTraek(this)) {
                // Prøver at udføre træk 
                Traek traek = new Traek(b.felt(), til, b, hentBrik(til));
                lavTraek(traek);
                // Hvis det resulterende bræt ikke er i skak imod spilleren
                // Så fortryd trækket og indiker at der ikke er skakmat.
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
