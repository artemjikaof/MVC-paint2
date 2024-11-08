package org.example.controller;

import org.example.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;

    private ActionDraw actionDraw;

    private static Controller instansce;
    public static  Controller getInstance(){
        if(instansce == null){
            instansce = new Controller();
        }
        return instansce;
    }
    private Controller() {

        model = new Model();
        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        sampleShape.setFb(new NoFill());
        model.setMyShape(sampleShape);
        actionDraw = new ActionDraw(model, sampleShape);
        panel = new MyPanel(this);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);


        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        frame.setJMenuBar(menuController.createMenuBar());
        panel.invalidate();
        panel.repaint();


    }

    public void mousePressedAction(Point p){
        actionDraw.createShape(p);
    }
    public void mouseDraggedAction(Point p){
        actionDraw.stretShape(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
