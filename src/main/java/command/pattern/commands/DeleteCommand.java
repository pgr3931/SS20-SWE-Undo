package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

public class DeleteCommand extends Command {

    public DeleteCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        saveBackup();
        text.deleteSelection();
        return true;
    }
}