package com.company.brikker;

import java.awt.Image;
import com.company.*;

public class Bonde extends Brik {
    public static Image billedHvid, billedSort;

    public Bonde(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public Felt[] tilladteTræk(Braet Braet) {
        return new Felt[0];
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
