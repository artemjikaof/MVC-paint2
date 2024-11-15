package org.example.model.factory;

import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;

public class MyShapeFactory {

    public MyShape createShape(ShapeType type, Color color, FillBehavior fb) {
        if(fb.getClass().equals(NoFill.class)){
            return new MyShape(type.createShape(),color, new NoFill());
        } else {
            return new MyShape(type.createShape(),color, new Fill());
        }
    }
}