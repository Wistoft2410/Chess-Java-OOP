package com.company;

import java.awt.*;
import java.awt.image.ImageObserver;

public class Felt {
    public int række, søjle;
    public final int uiStørrelse = 64;

    public Felt(int række, int søjle) {
        this.række = række;
        this.søjle = søjle;
    }

    // Denne metode er for at gøre det nemt at konvertere muse position til et skak felt
    public Felt(Point p) {
        this.række = 7 - p.y / uiStørrelse;
        this.søjle = p.x / uiStørrelse;
    }

    // Hjælpe metode til at skrive feltets navn i skak notation
    public String notation() {
        return "" + (char)('a' + søjle) + (række + 1);
    }

    // Finder ui x koordinatet svarende til feltet 
    private int x() {
        return søjle * uiStørrelse;
    }

    // Finder ui y koordinatet svarende til feltet 
    private int y() {
        return (7 - række) * uiStørrelse;
    }

    // Ser om feltet er på brættet
    public boolean lovligtFelt() {
        return række >= 0 && række < 8 && søjle >= 0 && søjle < 8;
    }

    // Sammenlignings metode til at sammmenligne med et andet felt
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Felt felt = (Felt) o;
        return række == felt.række && søjle == felt.søjle;
    }

    // Tegner feltet til ui, ved først at tegne farven og dernest navnet på feltet
    public void display(Graphics g) {
        if ((række + søjle) % 2 == 0) {
            g.setColor(new Color(139,69,19)); //mørke felter: g.setColor(new Color(235,235,208));
        }
        else {
            g.setColor(new Color(235,235,208)); //lyse felter: g.setColor(new Color(119,148,85));
        }
        g.fillRect(x(), y(), uiStørrelse, uiStørrelse);

        // Omvendt farve for felt navn
        if ((række + søjle) % 2 != 0) {
            g.setColor(new Color(139,69,19)); //mørke felter: g.setColor(new Color(235,235,208));
        }
        else {
            g.setColor(new Color(235,235,208)); //lyse felter: g.setColor(new Color(119,148,85));
        }
        g.drawString(notation(), x() + 2, y() + 12);
    }

    // Hjælpe metode til at tegne billeder på feltet
    public void display(Graphics g, Image billed, ImageObserver observer) {
        g.drawImage(billed, x(), y(), observer);
    }
}
