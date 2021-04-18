package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Konge extends Brik {
    public static Image billedHvid, billedSort;

    public Konge(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // Går igennem alle felter rundt om kongen og ser om der er en modstander eller om det er frit
        // og tilføjer dem som tilladte traek.
        // Bemærk at traekket hvor kongen bliver på samme plads også bliver afprøvet,
        // men siden at dette felt hverken er tomt eller indeholder en modstander bliver det ikke tilføjet.
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                Felt felt = new Felt(felt().række + x, felt().søjle + y);
                if(felt.lovligtFelt() && modstanderEllerFrit(braet, felt)) {
                    tilladte.add(felt);
                }
            }
        }
        return tilladte;
    }

    public Image billed() {
        if (erHvid()) {
            return billedHvid;
        }
        else {
            return billedSort;
        }
    }
}
