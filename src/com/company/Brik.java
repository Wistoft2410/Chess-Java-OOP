package com.company;

import java.util.LinkedList;

public class Brik {
    int xp;
    int yp;
    boolean isWhite;
    LinkedList<Brik> br;

    public Brik(int xp, int yp, boolean isWhite, LinkedList<Brik> br) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.br = br;
        br.add(this);
    }

    public void move(int xp, int yp){
        /* Tjek om der er en brik på positionen
           hvis der er en brik kan den dræbes,
           men hvis der ikke er en kan this.piece rykkes dertil */

        //for-loop der filtrer vores LinkedList gennem og sammenligner piece positionerne
        for(Brik p : br){
            if(p.xp == xp && p.yp == yp){
              p.fjern();
            }
        }
        this.xp=xp;
        this.yp=yp;
    }
    public void fjern(){
        br.remove(this);
    }
}
