package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.company.brikker.*;

public class Game {
    Braet braet = new Braet();
    Brik valgteBrik = null;
    Point valgteBrikPosition = null;
    boolean hvidsTur = true;

    public Game() {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);

        //ansvarlig for displaying af grafik og brikker
        JPanel grafik = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont( new Font("Times New Roman", Font.BOLD, 14));
                braet.displayFelter(g);
                braet.displayBrikker(g, this);
                // Tegner valgte brik ved siden af oven på mussens position
                if(valgteBrik != null && valgteBrikPosition != null) {
                    g.drawImage(valgteBrik.billed(), (int) valgteBrikPosition.getX(), (int) valgteBrikPosition.getY(), this);
                }
            }
        };
        frame.add(grafik);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e)  {
                // efter brikken er valgt skal den kunne rykkes på
                if(valgteBrik != null){
                    valgteBrikPosition = new Point(e.getX() - 32, e.getY() - 32); 
                    frame.repaint(); //det samme som frame update
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Finder ud af om mussen har trykket på en brik
                valgteBrik = braet.hentBrik(new Felt(e.getPoint()));
                if(valgteBrik != null) {
                    // Hvis den ikke er samme farve som den som har turet, skal det ignoreres
                    if(valgteBrik.erHvid() != hvidsTur) {
                        valgteBrik = null;
                        return;
                    }
                    // Ellers set den til at være valgt og set dens position
                    valgteBrik.setValgt(true);
                    valgteBrikPosition = new Point(e.getX() - 32, e.getY() - 32); 
                    frame.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Når mussen knappen slippes findes feltet den er blevet slippet på.
                // Dernæst prøves der at lave et træk svarende til det mussen har gjort.
                if(valgteBrik != null) {
                    Felt slutfelt = new Felt(e.getPoint());
                    valgteBrik.setValgt(false);
                    lavTraek(new Traek(valgteBrik.felt(), slutfelt, valgteBrik, braet.hentBrik(slutfelt)));
                }
                valgteBrik = null;
                valgteBrikPosition = null;
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

    }

    public static void hints(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Fejl type: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void lavTraek(Traek traek) {
        // Først ses der om trækket er lovligt for brikken
        if (!traek.brik.tilladteTraek(braet).contains(traek.til)) {
            return;
        }
        // Dernest ses der om det medføre skak for spilleren selv
        braet.lavTraek(traek);
        if(braet.iSkak(hvidsTur)) {
            // Hvis det gør annulleres trækket
            braet.fortrydTraek(traek);
            return;
        }
        // Ellers udføres trækket og turen gives videre
        hvidsTur = !hvidsTur;
        System.out.println("Udførte Træk: " + traek.til.notation());
        System.out.println("Spil status: " + status());
    }

    // Denne hjælper med at beslutte spillets status, ved først at se om der er skak mat, dernest skak og til sidst remis.
    public SpilStatus status() {
        if(braet.skakMat(hvidsTur)) {
            if(hvidsTur) {
                return SpilStatus.HvidISkakMat;
            }
            else {
                return SpilStatus.SortISkakMat;
            }
        }
        if(braet.iSkak(hvidsTur)) {
            if(hvidsTur) {
                return SpilStatus.HvidISkak;
            }
            else {
                return SpilStatus.SortISkak;
            }
        }
        if(braet.iSkak(!hvidsTur)) {
            if(!hvidsTur) {
                return SpilStatus.HvidISkak;
            }
            else {
                return SpilStatus.SortISkak;
            }
        }
        return SpilStatus.Igang;
    }
}
