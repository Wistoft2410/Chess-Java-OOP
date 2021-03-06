package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Dronning extends Brik {
    public static Image billedHvid, billedSort;

    public Dronning(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // Dronningen kan bevæge sig i alle retninger og derfor tilføjes alle traek i hver retning
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                lavTilladteTraekForRetning(tilladte, braet, x, y);
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
