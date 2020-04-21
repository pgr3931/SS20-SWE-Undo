package command.pattern.commands;

import command.pattern.OperationReceiver;

/**
 * Implementation for the cut command
 */
public class CutCommand extends Command {
    private String cutString;
    private int index;

    public CutCommand(OperationReceiver receiver) {
        super(receiver);
    }

    @Override
    public boolean execute() {
        cutString = operationReceiver.getSelection();
        operationReceiver.setClipBoard(cutString);
        index = operationReceiver.getIndex();
        operationReceiver.deleteSelection();
        return true;
    }

    @Override
    public void undo() {
        operationReceiver.insertText(cutString, index);
    }

    @Override
    public void redo() {
        operationReceiver.select(index, cutString.length());
        execute();
    }
}
