package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.OperationReceiver;

/**
 * Implementation for the paste command
 */
public class PasteCommand extends Command {
    private int start;
    private int end;

    public PasteCommand(OperationReceiver t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        operationReceiver.deleteText(start, end);
    }

    @Override
    public boolean execute() {
        String s = invoker.getClipBoard();
        start = operationReceiver.paste(s);
        end = start + s.length();
        return true;
    }
}
