package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Implementation for the delete command
 */
public class DeleteCommand extends Command {
    private String deletedString;
    private int index;

    public DeleteCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        text.insertText(deletedString, index);
    }

    @Override
    public boolean execute() {
        deletedString = text.getSelection();
        index = text.getIndex();
        text.deleteSelection();
        return true;
    }
}
