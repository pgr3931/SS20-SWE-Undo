package command.pattern;

import javafx.scene.control.TextArea;

/**
 * Receiver class
 * Executes all commands inside the editor window
 */
public class OperationReceiver {
    private TextArea area;

    public OperationReceiver(TextArea area) {
        this.area = area;
    }

    public String getText() {
        return area.getText();
    }

    public void select(int from, int to) {
        area.selectRange(from, to);
    }

    public String getSelection() {
        return area.getSelectedText();
    }

    public void deleteSelection() {
        area.replaceSelection("");
    }

    public int getIndex() {
        return area.getSelection().getStart();
    }

    public void insertText(String s, int i) {
        area.insertText(i, s);
    }

    public void deleteText(int start, int end) {
        area.deleteText(start, end);
    }

    public int paste(String s) {
        if (!area.getSelectedText().isEmpty()) {
            int start = getIndex();
            area.replaceSelection(s);
            return start;
        } else {
            int start = area.getCaretPosition();
            area.insertText(start, s);
            return start;
        }
    }
}
