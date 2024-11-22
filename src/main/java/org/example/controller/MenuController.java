package org.example.controller;
import org.example.controller.action.ActionDraw;
import org.example.controller.factory.MenuState;
import org.example.model.MyShape;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
public class MenuController {
    private static MenuController instance;
    private JMenuBar menuBar;
    private ActionDraw actionDraw;
    private MenuState state;
    private MenuController(){
        menuBar = createMenuBar();
    }
    public static MenuController getInstance(){
        if (instance == null){
            instance = new MenuController();
        }
        return instance;
    }

    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);

        return menuBar;
    }
    //    public enum ShapeType{
//        RECTANGULAR, ELLIPSE
//    }
    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        //поменять на фабрику
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> {
            MyShape sampleShape = actionDraw.getShape();
            sampleShape.setShape(new Rectangle2D.Double());
        });
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            MyShape sampleShape = actionDraw.getShape();
            sampleShape.setShape(new Ellipse2D.Double());
        });
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }
    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup group = new ButtonGroup();

        JMenuItem redItem = new JMenuItem("Красный");
        redItem.addActionListener(e -> state.setColor(Color.RED));
        colorMenu.add(redItem);

        JMenuItem greenItem = new JMenuItem("Зеленый");
        greenItem.addActionListener(e -> state.setColor(Color.GREEN));
        colorMenu.add(greenItem);

        JMenuItem blueItem = new JMenuItem("Синий");
        blueItem.addActionListener(e -> state.setColor(Color.BLUE));
        colorMenu.add(blueItem);

        return colorMenu;
    }
    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }
    public void  setState(MenuState state){
        this.state = state;
    }
}