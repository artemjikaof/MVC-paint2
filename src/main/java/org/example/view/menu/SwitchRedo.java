package org.example.view.menu;
import org.example.controller.state.UndoMachine;


public class SwitchRedo implements AppCommand {
    private UndoMachine undoMachine;
    @Override
    public void execute() {
        undoMachine.executeRedo();
        undoMachine.updateButtons();
    }
    public SwitchRedo(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }
}

