package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.OperationReceiver;
import javafx.scene.input.KeyCode;

public class TypeCommand extends Command {
    private int index;
    private boolean isShiftDown;
    public KeyCode code;
    private String deletedChar;

    public TypeCommand(OperationReceiver t, CommandInvoker i, int index, boolean isShiftDown, KeyCode code) {
        super(t, i);
        this.index = index;
        this.isShiftDown = isShiftDown;
        this.code = code;
    }

    @Override
    public void undo() {
        if (deletedChar == null) {
            try {
                operationReceiver.deleteText(index, index + 1);
            }catch (IndexOutOfBoundsException e){
                System.out.println(index);
            }
        } else if(isShiftDown){
            operationReceiver.insertText(deletedChar.toUpperCase(), index - 1);
        } else {
            operationReceiver.insertText(deletedChar, index - 1);
        }
    }

    @Override
    public boolean execute() {
        switch (code) {
            case BACK_SPACE:
                if(operationReceiver.getSelection().length() > 0){
                    deletedChar = operationReceiver.getSelection();
                    index = operationReceiver.getIndex() + 1;
                    operationReceiver.deleteSelection();
                    return true;
                }
                if (index > 0) {
                    deletedChar = operationReceiver.getText().substring(index - 1, index);
                    operationReceiver.deleteText(index - 1, index);
                    return true;
                }
                return false;
            case DELETE:
                if(operationReceiver.getSelection().length() > 0){
                    deletedChar = operationReceiver.getSelection();
                    index = operationReceiver.getIndex() + 1;
                    operationReceiver.deleteSelection();
                    return true;
                }
                if (index < operationReceiver.getText().length()) {
                    operationReceiver.deleteText(index, index + 1);
                    return true;
                }
                return false;
            case TAB:
                operationReceiver.insertText("\t", index);
                return true;
            case ENTER:
                operationReceiver.insertText("\n", index);
                return true;
            case SPACE:
                operationReceiver.insertText(" ", index);
                return true;
            case ALPHANUMERIC:
                if (isShiftDown) {
                    operationReceiver.insertText(code.getName().toUpperCase(), index);
                } else {
                    operationReceiver.insertText(code.getName(), index);
                }
                return true;
            case CAPS:
            case SHIFT:
                return false;
        }
        return true;
    }
}
