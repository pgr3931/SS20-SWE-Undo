package command.pattern;

import command.pattern.commands.Command;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Invoker/sender class
 */
public class CommandInvoker {
    private ObservableList<Command> history = FXCollections.observableArrayList();
    private SimpleIntegerProperty index = new SimpleIntegerProperty(-1);

    public void execute(Command c) {
        if (c.execute()) {
            removeHistoryFromIndex(); // [a, b, d]
            history.add(c);
            index.set(index.get() + 1);

            // delete the oldest command after 100 executions
            if (history.size() >= 100) {
                history.remove(0);
                index.set(index.get() - 1);
            }
        }
    }

    public void undo() {
        if (!history.isEmpty() && index.get() >= 0) {
            history.get(index.get()).undo();
            index.set(index.get() - 1);
        }
    }

    public void redo() {
        if (!history.isEmpty() && index.get() < history.size() - 1) {
            index.set(index.get() + 1);
            history.get(index.get()).redo();
        }
    }

    private void removeHistoryFromIndex() {
        if (index.get() == -1) {
            history.clear();
        } else if (!history.isEmpty() && index.get() < history.size() - 1) {
            history.remove(index.get() + 1, history.size());
        }
    }

    /**
     * Only used by the main controller to disable buttons
     * Not part of the pattern
     */
    public ObservableList<Command> getHistory() {
        return history;
    }

    public SimpleIntegerProperty getIndex() {
        return index;
    }
}
