package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Springer extends Brik {
    public static Image billedHvid, billedSort;
    boolean harFlyttet = false;

    public Springer(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        int[] bevægelse = {-2, -1, 1, 2};
        for(int x: bevægelse) {
            for(int y: bevægelse) {
                // En springer bevæger sig altid 2 i en akse og 1 i en anden akse.
                if(Math.abs(x) == Math.abs(y)) {
                    continue;
                }
                Felt felt = new Felt(felt().række + x, felt().søjle + y);
                if(felt.lovligtFelt()) {
                    Brik b = braet.hentBrik(felt);
                    if(b == null || b.erHvid() != erHvid()) {
                        tilladte.add(felt);
                    }
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
