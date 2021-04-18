package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Taarn extends Brik {
    public static Image billedHvid, billedSort;

    public Taarn(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // Tilføjer alle traek som følger akserne
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                // Dette er for at undgå diagonale retninger i loopet
                if(x != 0 && y != 0) {
                    continue;
                }
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
