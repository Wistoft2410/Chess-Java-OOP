package com.company.brikker;

import com.company.Braet;
import com.company.Felt;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Loeber extends Brik {
    public static Image billedHvid, billedSort;

    public Loeber(Felt felt, boolean erHvid) {
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
