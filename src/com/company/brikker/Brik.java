package com.company.brikker;
import com.company.Braet;
import com.company.Felt;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

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
    public abstract List<Felt> tilladteTraek(Braet braet);
    //metode til at
    public void flytTil(Felt plads) {
        this.plads = plads;
    }

    public Felt felt() {
        return this.plads;
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

    void lavTilladteTraekForRetning(List<Felt> tilladte, Braet braet, int xd, int yd) {
        int x = felt().række + xd ;
        int y = felt().søjle + yd;
        Felt felt = new Felt(x, y);
        while(felt.lovligtFelt() && braet.hentBrik(felt) == null) {
            tilladte.add(felt);
            x += xd;
            y += yd;
            felt = new Felt(x, y);
        }
        if(felt.lovligtFelt() && modstanderEllerFrit(braet, felt)) {
            tilladte.add(felt);
        }
    }

    boolean modstanderEllerFrit(Braet braet, Felt felt) {
        Brik b = braet.hentBrik(felt);
        return b == null || b.erHvid() != erHvid();
    }
}
