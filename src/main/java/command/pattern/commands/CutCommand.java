package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CutCommand extends Command {

    public CutCommand(Text t, CommandInvoker i) {
        super(t, i);
    }

    @Override
    public boolean execute() {
        saveBackup();

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(text.getSelection()),
                        null
                );

        text.deleteSelection();
        return true;
    }
}
