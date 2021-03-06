package command.pattern.commands;

import command.pattern.OperationReceiver;
import javafx.scene.input.KeyCode;

/**
 * Implementation for the type command
 */
public class TypeCommand extends Command {
    private int index;
    private boolean isShiftDown;
    private KeyCode code;
    private String deletedChar;

    public TypeCommand(OperationReceiver receiver, boolean isShiftDown, KeyCode code) {
        super(receiver);
        this.isShiftDown = isShiftDown;
        this.code = code;
    }

    @Override
    public boolean execute() {
        index = operationReceiver.getCaretPosition();

        if (code.isLetterKey() || code.isDigitKey()) {
            if (isShiftDown) {
                operationReceiver.insertText(code.getName(), index);
            } else {
                operationReceiver.insertText(code.getName().toLowerCase(), index);
            }
            return true;
        }

        switch (code) {
            case BACK_SPACE:
                if (operationReceiver.getSelection().length() > 0) {
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
                if (operationReceiver.getSelection().length() > 0) {
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
            case PERIOD:
                if (isShiftDown) {
                    operationReceiver.insertText(":", index);
                } else {
                    operationReceiver.insertText(".", index);
                }
                return true;
            case COMMA:
                if (isShiftDown) {
                    operationReceiver.insertText(";", index);
                } else {
                    operationReceiver.insertText(",", index);
                }
                return true;
            case MINUS:
                if (isShiftDown) {
                    operationReceiver.insertText("_", index);
                } else {
                    operationReceiver.insertText("-", index);
                }
                return true;

            //And so on...
        }
        return false;
    }

    @Override
    public void undo() {
        if (deletedChar == null) {
            operationReceiver.deleteText(index, index + 1);
        } else if (isShiftDown) {
            operationReceiver.insertText(deletedChar.toUpperCase(), index - 1);
        } else {
            operationReceiver.insertText(deletedChar, index - 1);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + code;
    }
}
