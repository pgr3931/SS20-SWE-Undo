package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Abstract command class
 */
public abstract class Command {
    protected Text text;
    private String backup;
    protected CommandInvoker invoker;

    public Command(Text t, CommandInvoker i) {
        text = t;
        invoker = i;
    }

    /**
     * Saves the current state of the text editor
     */
    public void saveBackup() {
        backup = text.getText();
    }

    /**
     * Reverts the text back to the last state
     */
    public void undo() {
        text.setText(backup);
    }

    public abstract boolean execute();
}
