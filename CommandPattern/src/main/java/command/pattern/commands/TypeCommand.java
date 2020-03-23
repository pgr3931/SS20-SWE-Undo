package command.pattern.commands;

import command.pattern.CommandInvoker;
import command.pattern.Text;
import javafx.scene.input.KeyCode;

public class TypeCommand extends Command {
    private int index;
    private boolean isShiftDown;
    public KeyCode code;
    private String deletedChar;

    public TypeCommand(Text t, CommandInvoker i, int index, boolean isShiftDown, KeyCode code) {
        super(t, i);
        this.index = index;
        this.isShiftDown = isShiftDown;
        this.code = code;
    }

    @Override
    public void undo() {
        if (deletedChar == null) {
            try {
                text.deleteText(index, index + 1);
            }catch (IndexOutOfBoundsException e){
                System.out.println(index);
            }
        } else if(isShiftDown){
            text.insertText(deletedChar.toUpperCase(), index - 1);
        } else {
            text.insertText(deletedChar, index - 1);
        }
    }

    @Override
    public boolean execute() {
        switch (code) {
            case BACK_SPACE:
                if(text.getSelection().length() > 0){
                    deletedChar = text.getSelection();
                    index = text.getIndex() + 1;
                    text.deleteSelection();
                    return true;
                }
                if (index > 0) {
                    deletedChar = text.getText().substring(index - 1, index);
                    text.deleteText(index - 1, index);
                    return true;
                }
                return false;
            case DELETE:
                if(text.getSelection().length() > 0){
                    deletedChar = text.getSelection();
                    index = text.getIndex() + 1;
                    text.deleteSelection();
                    return true;
                }
                if (index < text.getText().length()) {
                    text.deleteText(index, index + 1);
                    return true;
                }
                return false;
            case TAB:
                text.insertText("\t", index);
                return true;
            case ENTER:
                text.insertText("\n", index);
                return true;
            case SPACE:
                text.insertText(" ", index);
                return true;
            case ALPHANUMERIC:
                if (isShiftDown) {
                    text.insertText(code.getName().toUpperCase(), index);
                } else {
                    text.insertText(code.getName(), index);
                }
                return true;
            case CAPS:
            case SHIFT:
                return false;
        }
        return true;
    }
}
