package command.pattern.commands;

import command.pattern.OperationReceiver;

/**
 * Abstract command class
 */
public abstract class Command {
    protected OperationReceiver operationReceiver;

    public Command(OperationReceiver receiver) {
        operationReceiver = receiver;
    }

    public abstract boolean execute();

    public abstract void undo();

    public void redo() {
        execute();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
