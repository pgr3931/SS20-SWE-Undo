package command.pattern.commands;

import command.pattern.OperationReceiver;

/**
 * Implementation for the delete command
 */
public class DeleteCommand extends Command {
    private String deletedString;
    private int index;

    public DeleteCommand(OperationReceiver t) {
        super(t);
    }

    @Override
    public boolean execute() {
        deletedString = operationReceiver.getSelection();
        index = operationReceiver.getIndex();
        operationReceiver.deleteSelection();
        return true;
    }

    @Override
    public void undo() {
        operationReceiver.insertText(deletedString, index);
    }

    @Override
    public void redo() {
        operationReceiver.select(index, deletedString.length());
        execute();
    }

}
