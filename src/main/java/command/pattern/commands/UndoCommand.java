package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Implementation for the undo command
 */
public class UndoCommand extends Command {

    public UndoCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public void undo() {
        // would be redo
        throw new NotImplementedException();
    }

    @Override
    public boolean execute() {
        invoker.undo();
        return false;
    }
}
