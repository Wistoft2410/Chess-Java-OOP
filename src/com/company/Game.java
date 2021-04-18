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

    public Game() {
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);

        //ansvarlig for displaying af grafik og brikker
        JPanel grafik = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                braet.display(g, this);
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
                //System.out.println((hentBrik(e.getX(),e.getY()).isWhite?"white ":"black ")+hentBrik(e.getX(),e.getY()).type);
                valgteBrik = braet.hentBrik(new Felt(e.getPoint()));
                if(valgteBrik != null) {
                    valgteBrik.setValgt(true);
                    valgteBrikPosition = new Point(e.getX() - 32, e.getY() - 32); 
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //når mussens knap frigives skal brikken placeres, hvilket er årsagen til at vi igen dividere med 64
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
        System.out.println(traek.til.notation());
        braet.fjernBrik(traek.til);
        traek.brik.flytTil(traek.til);
    }

    //public static Brik hentBrik(int x, int y){

    //    int xp = x/64;
    //    int yp = y/64;

    //    for(Brik b: bb){
    //        if(b.xp == xp && b.yp == yp){
    //            return b;
    //        }
    //    }
    //    return null;
    //}
}
