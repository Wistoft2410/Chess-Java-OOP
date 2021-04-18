package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Loeber extends Brik {
    public static Image billedHvid, billedSort;

    public Loeber(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // tilføjer alle traek som er diagonale retninger
        for(int x = -1; x <= 1; x+= 2) {
            for(int y = -1; y <= 1; y += 2) {
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
