package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

/**
 * Abstract command class
 */
public abstract class Command {
    protected Text text;
    protected CommandInvoker invoker;

    public Command(Text t, CommandInvoker i) {
        text = t;
        invoker = i;
    }

    public abstract void undo();

    public abstract boolean execute();
}
