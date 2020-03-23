package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Implementation for the cut command
 */
public class CutCommand extends Command {
    private String cutString;
    private int index;

    public CutCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        text.insertText(cutString, index);
    }

    @Override
    public boolean execute() {
        cutString = text.getSelection();
        invoker.setClipBoard(cutString);
        index = text.getIndex();
        text.deleteSelection();
        return true;
    }
}
