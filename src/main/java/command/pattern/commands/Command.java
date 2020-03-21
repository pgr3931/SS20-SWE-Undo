package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;

public abstract class Command {
    protected Text text;
    private String backup;
    protected CommandInvoker invoker;

    public Command(Text t, CommandInvoker i){
        text = t;
        invoker = i;
    }

    public void saveBackup(){
        backup = text.getText();
    }

    public void undo(){
        text.setText(backup);
    }

    public abstract boolean execute();
}
