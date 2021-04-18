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
