package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Implementation for the paste command
 */
public class PasteCommand extends Command {
    private int start;
    private int end;

    public PasteCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        text.deleteText(start, end);
    }

    @Override
    public boolean execute() {
        String s = invoker.getClipBoard();
        start = text.paste(s);
        end = start + s.length();
        return true;
    }
}
