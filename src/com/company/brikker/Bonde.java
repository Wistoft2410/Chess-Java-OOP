package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Bonde extends Brik {
    public static Image billedHvid, billedSort;
    boolean harFlyttet = false;

    public Bonde(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public void flytTil(Felt plads) {
        super.flytTil(plads);
        harFlyttet = true;
    }
    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        int fremad = 0;
        if(erHvid()) {
            fremad = 1;
        }
        else {
            fremad = -1;
        }
        Felt fremadFelt = new Felt(felt().række + fremad, felt().søjle);
        if(fremadFelt.lovligtFelt() && braet.hentBrik(fremadFelt) == null) {
            tilladte.add(fremadFelt);
        }
        Felt venstre = new Felt(felt().række + fremad, felt().søjle - 1);
        Brik venstreBrik = braet.hentBrik(venstre);
        if(venstre.lovligtFelt() && venstreBrik != null && venstreBrik.erHvid() != erHvid()) {
            tilladte.add(venstre);
        }
        Felt højre = new Felt(felt().række + fremad, felt().søjle + 1);
        Brik højreBrik = braet.hentBrik(højre);
        if(højre.lovligtFelt() && højreBrik != null && højreBrik.erHvid() != erHvid()) {
            tilladte.add(højre);
        }
        if(!harFlyttet) {
            Felt dobbeltFremad = new Felt(felt().række + fremad * 2, felt().søjle);
            if(braet.hentBrik(dobbeltFremad) == null) {
                tilladte.add(dobbeltFremad);
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
