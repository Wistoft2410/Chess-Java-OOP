package com.company.brikker;

import java.awt.Image;
import com.company.*;
import java.util.List;
import java.util.LinkedList;

public class Bonde extends Brik {
    public static Image billedHvid, billedSort;

    public Bonde(Felt felt, boolean erHvid) {
        super(felt, erHvid);
    }

    public List<Felt> tilladteTraek(Braet braet) {
        List<Felt> tilladte = new LinkedList<Felt>();
        // Finder først ud af hvad der er fremad for bonden
        int fremad = 0;
        if(erHvid()) {
            fremad = 1;
        }
        else {
            fremad = -1;
        }
        // Checker om feltet fremad er tomt
        Felt fremadFelt = new Felt(felt().række + fremad, felt().søjle);
        if(fremadFelt.lovligtFelt() && braet.hentBrik(fremadFelt) == null) {
            tilladte.add(fremadFelt);
        }
        // Checker om fremad til venstre har en modstander brik
        Felt venstre = new Felt(felt().række + fremad, felt().søjle - 1);
        Brik venstreBrik = braet.hentBrik(venstre);
        if(venstre.lovligtFelt() && venstreBrik != null && venstreBrik.erHvid() != erHvid()) {
            tilladte.add(venstre);
        }
        // Checker om fremad til højre har en modstander brik
        Felt højre = new Felt(felt().række + fremad, felt().søjle + 1);
        Brik højreBrik = braet.hentBrik(højre);
        if(højre.lovligtFelt() && højreBrik != null && højreBrik.erHvid() != erHvid()) {
            tilladte.add(højre);
        }
        // Checker om bonden må gå 2 fremad, ved at se om den har bevæget sig endnu
        if(erHvid() && felt().række == 1 || !erHvid() && felt().række == 6) {
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
