package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

public class UndoCommand extends Command {

    public UndoCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        invoker.undo();
        return false;
    }
}
