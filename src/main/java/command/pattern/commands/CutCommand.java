package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Implementation for the cut command
 */
public class CutCommand extends Command {

    public CutCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        saveBackup();
        invoker.setClipBoard(text.getSelection());
        text.deleteSelection();
        return true;
    }
}
