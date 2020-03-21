package command.pattern;

import command.pattern.commands.Command;

import java.util.LinkedList;

/**
 * Invoker/sender class
 */
public class CommandInvoker {
    private LinkedList<Command> history = new LinkedList<>();

    public void execute(Command c) {
        if (c.execute()) {
            history.add(c);
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            history.removeLast().undo();
        }
    }
}
