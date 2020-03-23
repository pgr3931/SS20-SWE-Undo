package command.pattern;

import command.pattern.commands.Command;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Invoker/sender class
 */
public class CommandInvoker {
    private ObservableList<Command> history = FXCollections.observableArrayList();
    private SimpleStringProperty clipBoard = new SimpleStringProperty();

    public void execute(Command c) {
        if (c.execute()) {
            history.add(c);
            // delete the oldest command after 100 executions
            if (history.size() >= 100) {
                history.remove(0);
            }
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            history.remove(history.size() - 1).undo();
        }
    }

    public void setClipBoard(String clipBoard) {
        this.clipBoard.set(clipBoard);
    }

    public String getClipBoard() {
        return clipBoard.get();
    }

    /**
     * Only used by the main controller to disable buttons
     * Not part of the pattern
     */
    public ObservableList<Command> getHistory() {
        return history;
    }

    public SimpleStringProperty clipBoardProperty() {
        return clipBoard;
    }
}
