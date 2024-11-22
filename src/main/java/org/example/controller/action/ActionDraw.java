package org.example.controller.action;

import org.example.controller.factory.ShapeCreationFactory;
import org.example.model.Model;
import org.example.model.MyShape;
import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction{
    private Model model;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;

    private ShapeCreationFactory shapeCreationFactory;

    public MyShape getShape() {
        return shape;
    }
    public ActionDraw(Model model, MyShape shape){
        this.model = model;
        this.shape = shape;
        shapeCreationFactory = ShapeCreationFactory.getInstance();
    }
    @Override
    public void mouseDragged(Point point){
        secondPoint = point;
        shape.setFrame(firstPoint, secondPoint);
        model.update();
    }

    @Override
    public void mousePressed(Point point){
        firstPoint = point;
        shape = shapeCreationFactory.createShape();
        model.createCurrentShape(shape);
        model.update();

    }
}
