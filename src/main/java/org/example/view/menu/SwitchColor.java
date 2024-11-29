package org.example.view.menu;
import lombok.AllArgsConstructor;
import org.example.controller.factory.MenuState;

import javax.swing.*;
import java.awt.*;
@AllArgsConstructor
public class SwitchColor implements AppCommand {
    private JRadioButtonMenuItem radioButton;
    private MenuState menuState;
    private boolean useDefault;
    private Color defaultColor;

    public SwitchColor(JRadioButtonMenuItem radioButton, boolean useDefault, MenuState menuState, Color defaultColor) {
        this.radioButton = radioButton;
        this.useDefault = useDefault;
        this.menuState = menuState;
        this.defaultColor = defaultColor;
    }


    @Override
    public void execute() {
        Color color = useDefault ? defaultColor : JColorChooser.showDialog(null, "Выбор цвета", Color.BLACK);
        menuState.setColor(color);
    }

}
