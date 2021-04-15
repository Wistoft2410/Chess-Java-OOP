package com.company;

import java.util.LinkedList;


public class Brik {
    int xp; // variabler til placering af brikkerne
    int yp;

    int x;
    int y;

    boolean isWhite; //primitiv datatype bool til at holde styr på brik farve
    String type; //string variable til at holde styr på brik-type
    LinkedList<Brik> bb; //"container" til at opbevare brikkerne


    public Brik(int xp, int yp, boolean isWhite, String t, LinkedList<Brik> bb) {
        this.xp = xp;
        this.yp = yp;

        x = xp * 64; //placering af brikkerne indenfor 8x8 brættet
        y = yp * 64;

        this.isWhite = isWhite;

        this.bb = bb;

        type = t;
        bb.add(this); //data tilføjes til ny instans af bestemt brik

    }
}
