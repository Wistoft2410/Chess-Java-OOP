package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Springer extends Brik {
    public static Image billedHvid, billedSort;

    public Springer(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // Springeren bevæger sig 2 i en akse og 1 i en anden akse.
        int[] bevægelse = {-2, -1, 1, 2};
        for(int x: bevægelse) {
            for(int y: bevægelse) {
                // Her fjerner vi alle traek hvor at den bevæger sig 2 i hver akse eller 1 i hver akse.
                if(Math.abs(x) == Math.abs(y)) {
                    continue;
                }
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
