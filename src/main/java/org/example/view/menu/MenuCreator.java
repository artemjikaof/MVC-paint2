package org.example.view.menu;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.controller.factory.MenuState;
import org.example.controller.factory.ShapeType;
import org.example.controller.state.UndoMachine;
import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;


public class MenuCreator {
    private static MenuCreator instance;
    private JMenuBar menuBar;
    private AppAction action;
    private MenuState state;
    private MyShape shape;
    private Model model;
    private JRadioButtonMenuItem rgbButton;

    private UndoMachine undoMachine;

    private MenuCreator(){
        menuBar = createMenuBar();
    }
    public static MenuCreator getInstance(){
        if (instance == null){
            instance = new MenuCreator();
        }
        return instance;
    }

    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu shapeMenu = createShapeMenu();

        JMenu colorMenu = createColorMenu();

        menuBar.add(shapeMenu);

        menuBar.add(colorMenu);

        JMenu actionMenu = createActionMenu();
        menuBar.add(actionMenu);

        JMenu fillMenu = createFillMenu();
        menuBar.add(fillMenu);


        return menuBar;
    }
    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        //поменять на фабрику
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> {
            state.setShapeType(ShapeType.RECTANGLE);
        });
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            state.setShapeType(ShapeType.ELLIPSE);
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

        rgbButton = new JRadioButtonMenuItem("Другой");
        rgbButton.addActionListener(new CommandActionListener(new SwitchColor(rgbButton, false, state, null)));
        colorMenu.add(rgbButton);
        group.add(rgbButton);

        return colorMenu;
    }

    private JMenu createActionMenu() {
        JMenu shapeMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Рисовать");
        square.addActionListener(e -> state.setAction(new ActionDraw(model, shape)));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Двигать");
        ellipse.addActionListener(e -> state.setAction(new ActionMove(model)));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }


    private JMenu createFillMenu(){
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem yes = new JRadioButtonMenuItem("Есть");
        yes.addActionListener(e -> state.setFill(true));
        fillMenu.add(yes);
        group.add(yes);
        JRadioButtonMenuItem no = new JRadioButtonMenuItem("Нет");
        no.addActionListener(e -> state.setFill(false));
        fillMenu.add(no);
        group.add(no);
        return fillMenu;
    }


    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();

        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }


    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(rgbButton, false,  state, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));


        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(false, state);
        menuItems.add(new CommandActionListener("Пусто",noFillIco,noFillCommand));


        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(true, state);
        menuItems.add(new CommandActionListener("Заливка", fillIco, fillCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(ShapeType.RECTANGLE, state);
        menuItems.add(new CommandActionListener("Фигура", rectangularIco, rectangularCommand));

        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(ShapeType.ELLIPSE, state);
        menuItems.add(new CommandActionListener("Фигура", ellipseIco, ellipseCommand));

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(state, new ActionDraw(model, shape));
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(state, new ActionMove(model));
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));

        URL redoUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand redoC = new SwitchRedo(undoMachine);
        menuItems.add(new CommandActionListener("Действие", redoIco, redoC));

        URL undoUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand undoC = new SwitchUndo(undoMachine);
        menuItems.add(new CommandActionListener("Действие", undoIco, undoC));


        return menuItems;

    }

    public void  setState(MenuState state){
        this.state = state;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}