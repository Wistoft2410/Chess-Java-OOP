package com.company;

import java.util.LinkedList;

public class Brik {
    int xp; //variabler til placering af brikker
    int yp;

    int x;//variabler til bevægelse af brikker vha. gange på position variablerne (xp&yp)
    int y;
    boolean isWhite; //primitiv datatype bool til at holde styr på brik farve
    String type; //string variable til at holde styr på brik-type
    LinkedList<Brik> br; //"container" til at opbevare brikkerne


    public Brik(int xp, int yp, boolean isWhite, String t, LinkedList<Brik> br) {
        this.xp = xp;
        this.yp = yp;

        this.isWhite = isWhite;
        this.br = br;
        type = t; //Ikke this. da den bruges andre steder
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
