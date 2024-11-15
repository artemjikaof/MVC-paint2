package org.example.controller.factory;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;

import java.awt.*;

@Getter
@Setter
public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private ActionDraw action;

    public MenuState(){
        shapeType= ShapeType.RECTANGLE;
        color = Color.BLUE;
        fill = false;
    }
}