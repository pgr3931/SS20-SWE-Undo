package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.OperationReceiver;

/**
 * Implementation for the cut command
 */
public class CutCommand extends Command {
    private String cutString;
    private int index;

    public CutCommand(OperationReceiver t, CommandInvoker i) {
        super(t, i);
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

    @Override
    public boolean execute() {
        cutString = operationReceiver.getSelection();
        invoker.setClipBoard(cutString);
        index = operationReceiver.getIndex();
        System.out.println(index);
        operationReceiver.deleteSelection();
        return true;
    }
}
