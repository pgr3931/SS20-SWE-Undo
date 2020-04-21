package command.pattern.commands;

import command.pattern.OperationReceiver;

/**
 * Implementation for the paste command
 */
public class PasteCommand extends Command {
    private int start;
    private int end;

    public PasteCommand(OperationReceiver t) {
        super(t);
    }

    @Override
    public boolean execute() {
        String s = operationReceiver.getClipBoard();
        start = operationReceiver.paste(s);
        end = start + s.length();
        return true;
    }

    @Override
    public void undo() {
        operationReceiver.deleteText(start, end);
    }
}
