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

    public Felt(Point p) {
        this.række = 7 - p.y / uiStørrelse;
        this.søjle = p.x / uiStørrelse;
    }

    public String notation() {
        return "" + (char)('a' + søjle) + (række + 1);
    }

    private int x() {
        return søjle * uiStørrelse;
    }

    private int y() {
        return (7 - række) * uiStørrelse;
    }

    public boolean lovligtFelt() {
        return række >= 0 && række < 8 && søjle >= 0 && søjle < 8;
    }

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

    public void display(Graphics g, Image billed, ImageObserver observer) {
        g.drawImage(billed, x(), y(), observer);
    }
}
