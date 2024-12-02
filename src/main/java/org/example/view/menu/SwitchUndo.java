package org.example.view.menu;

import org.example.controller.state.UndoMachine;

public class SwitchUndo implements AppCommand{

    private UndoMachine undoMachine;
    @Override
    public void execute() {
        undoMachine.executeUndo();
        undoMachine.updateButtons();
    }

    public SwitchUndo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }
}
