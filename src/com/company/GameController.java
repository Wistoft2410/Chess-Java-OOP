package com.company;

import com.company.brikker.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameController {

    public static void main(String[] args) throws IOException {
        //orginaltBrikBillede importeres så de enkelte brikker kan blive skåret ud
        BufferedImage orginaltBrikBillede = ImageIO.read(new File("/Users/computer/Documents/GitHub/Chess-Java-OOP/data/brikker.png"));

        Image brikker[] = new Image[12];

        int isoleretBrik = 0;

        for(int y =0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                brikker[isoleretBrik] = orginaltBrikBillede.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                isoleretBrik++;
            }
        }
        // Sætter billederne for de forskellige brik typer
        Bonde.billedHvid = brikker[5];
        Bonde.billedSort = brikker[5 + 6];
        Springer.billedHvid = brikker[3];
        Springer.billedSort = brikker[3 + 6];
        Loeber.billedHvid = brikker[2];
        Loeber.billedSort = brikker[2 + 6];
        Taarn.billedHvid = brikker[4];
        Taarn.billedSort = brikker[4 + 6];
        Dronning.billedHvid = brikker[1];
        Dronning.billedSort = brikker[1 + 6];
        Konge.billedHvid = brikker[0];
        Konge.billedSort = brikker[0 + 6];

        new Game(); // if(gg == true){sout("Hav et godt spil!") prog memes...

    }
}
