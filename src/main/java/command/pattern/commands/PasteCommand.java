package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

public class PasteCommand extends Command {

    public PasteCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        text.paste();
        return true;
    }
}
