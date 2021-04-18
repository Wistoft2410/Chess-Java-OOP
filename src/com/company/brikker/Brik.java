package com.company.brikker;
import com.company.Braet;
import com.company.Felt;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.List;

public abstract class Brik {
    Felt plads;
    boolean erHvid; 
    // Bliver brugt til at stopppe med at tegne en brik på normalt hvis, hvis den markeret med cursoren,
    // da brikken så skal følge mussen og ikke længere tegnes på brættet.
    boolean valgt = false;

    public Brik(Felt plads, boolean erHvid) {
        this.plads = plads;
        this.erHvid = erHvid;
    }

    // Denne metode skal nedarvede brikker implementere således at de returnere det billed som skal bruges
    // Når brikken skal vises i ui.
    public abstract Image billed();

    // Denne metode skal returnere en liste af alle tilladte træk en brik kan lave.
    // Dette skal ikke tage højde for om trækket resulterer i skak for en selv, som ikke er et gyldigt træk.
    // Sådanne træk bliver taget hånd om i lavTraek i spillet.
    public abstract List<Felt> tilladteTraek(Braet braet);

    //metode til at flytte brikkens plads
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

    // Konstruere traek som kan laves hvis brikken kan gå i bestemt retning.
    // Stopper hvis feltet er ude for brættet eller hvis feltet er bag en en brik 
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

    // Checker returnere sand hvis feltet har en modstander på sig eller er fri.
    boolean modstanderEllerFrit(Braet braet, Felt felt) {
        Brik b = braet.hentBrik(felt);
        return b == null || b.erHvid() != erHvid();
    }
}
