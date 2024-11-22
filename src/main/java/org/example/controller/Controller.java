package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeCreationFactory;
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

    private MenuState state;

    private static Controller instansce;
    public static  Controller getInstance(){
        if(instansce == null){
            instansce = new Controller();
        }
        return instansce;
    }
    private Controller() {
        state = new MenuState();
        ShapeCreationFactory shapeCreationFactory = ShapeCreationFactory.getInstance();
        shapeCreationFactory.config(state);

        model = new Model();
        MyShape sampleShape = shapeCreationFactory.createShape();
        sampleShape.setFb(new NoFill());
        actionDraw = new ActionDraw(model, sampleShape);
        panel = new MyPanel(this);
        state.setActionDraw(actionDraw);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);


        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        menuController.setState(state);
        frame.setJMenuBar(menuController.createMenuBar());
        frame.revalidate();


    }

    public void mousePressedAction(Point p){
        actionDraw.mousePressed(p);
    }
    public void mouseDraggedAction(Point p){
        actionDraw.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
