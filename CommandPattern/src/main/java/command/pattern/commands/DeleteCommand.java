package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.OperationReceiver;

/**
 * Implementation for the delete command
 */
public class DeleteCommand extends Command {
    private String deletedString;
    private int index;

    public DeleteCommand(OperationReceiver t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        operationReceiver.insertText(deletedString, index);
    }

    @Override
    public boolean execute() {
        deletedString = operationReceiver.getSelection();
        index = operationReceiver.getIndex();
        operationReceiver.deleteSelection();
        return true;
    }
}
