package com.company;

import com.company.brikker.Brik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    Braet braet = new Braet();
    Brik valgteBrik = null;
    Point valgteBrikPosition = null;
    boolean hvidsTur = true;

    // UI elementer
    JFrame frame;
    boolean iMenu = true;
    JPanel spilGrafik; 
    JButton menuStart;
    JButton menuSlut;

    public Game() {
        frame = new JFrame();
        frame.setBounds(10, 10, 512, 540);
        frame.setUndecorated(false);

        // Laver start og afslut menu knapper
        menuStart = new JButton("Start Spil");
        menuSlut = new JButton("Afslut");
        menuStart.setBounds(512 / 2 - 128/2, 200, 128, 64);
        menuSlut.setBounds(512 / 2 - 128/2, 300, 128, 64);
        menuStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                startSpil();
            }
        });
        menuSlut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){  
                frame.setVisible(false);
                frame.dispose();
            }
        });

        //ansvarlig for displaying af grafik og brikker
        spilGrafik = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setFont( new Font("Times New Roman", Font.BOLD, 14));
                if(!iMenu) {
                    braet.displayFelter(g);
                    braet.displayBrikker(g, this);
                    // Tegner valgte brik ved siden af oven på mussens position
                    if(valgteBrik != null && valgteBrikPosition != null) {
                        g.drawImage(valgteBrik.billed(), (int) valgteBrikPosition.getX(), (int) valgteBrikPosition.getY(), this);
                    }
                }
            }
        };

        spilGrafik.setFocusable(true);
        spilGrafik.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                System.out.println("Nyt parti");
                if(e.getKeyChar() == ' ' && !iMenu) {
                    startMenu();
                }
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

        frame.add(menuStart);
        frame.add(menuSlut);
        frame.add(spilGrafik);
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

    // Fjerner menuen og tilføjer spillet grafisk
    private void startSpil() {
        hvidsTur = true;
        braet = new Braet();
        iMenu = false;
        menuStart.setVisible(false);
        menuSlut.setVisible(false);
        frame.repaint();
    }

    private void startMenu() {
        iMenu = true;
        menuStart.setVisible(true);
        menuSlut.setVisible(true);
        frame.repaint();
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
