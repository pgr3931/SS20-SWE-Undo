package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Implementation for the paste command
 */
public class PasteCommand extends Command {

    public PasteCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        saveBackup();
        text.paste(invoker.getClipBoard());
        return true;
    }
}
