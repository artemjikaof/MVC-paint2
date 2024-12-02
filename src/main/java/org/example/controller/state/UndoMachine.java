package org.example.controller.state;
import org.example.controller.action.AppAction;
import java.util.LinkedList;
import org.example.view.menu.CommandActionListener;
import lombok.Getter;
import lombok.Setter;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    @Setter
    private CommandActionListener undoActionListener;

    @Setter
    private CommandActionListener redoActionListener;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return undoRedoState.getUndoActivityList().size() > 0;
    }


    public boolean isEnableRedo() {
        return undoRedoState.getRedoActivityList().size() > 0;
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        //TODO: Определить переход по состоянию
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }

    public void updateButtons() {
        undoActionListener.setEnabled(isEnableUndo());
        redoActionListener.setEnabled(isEnableRedo());


    }
}
