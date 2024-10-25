package org.example.view;

import org.example.controller.Singleton;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements Observer {
    private Singleton singleton;

    public MyPanel(Singleton singleton) {
        this.singleton = singleton;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                singleton.getPointOne(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                singleton.getPointTwo(arg0.getPoint());
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        singleton.draw(g2);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}