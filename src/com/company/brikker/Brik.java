package com.company.brikker;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import com.company.*;

public abstract class Brik {
    Felt plads;
    boolean erHvid; 
    // Bliver brugt til at stopppe med at tegne en brik på normalt hvis, hvis den markeret med cursoren.
    boolean valgt = false;

    public Brik(Felt plads, boolean erHvid) {
        this.plads = plads;
        this.erHvid = erHvid;
    }

    public abstract Image billed();
    public abstract Felt[] tilladteTræk(Braet Braet);
    //metode til at
    public void flytTil(Felt plads) {
        this.plads = plads;
    }

    public void display(Graphics g, ImageObserver observer) {
        if(!valgt) {
            plads.display(g, billed(), observer);
        }
    }
    public boolean erHvid() {
        return erHvid;
    }
    public void setValgt(boolean valgt) {
        this.valgt = valgt;
    }
}
