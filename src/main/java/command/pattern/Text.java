package command.pattern;

import javafx.scene.control.TextArea;

/**
 * Receiver class
 * Executes all commands inside the editor window
 */
public class Text {
    private TextArea area;

    public Text(TextArea area) {
        this.area = area;
    }

    public String getText() {
        return area.getText();
    }

    public void setText(String s) {
        area.setText(s);
        area.positionCaret(area.getText().length());
    }

    public String getSelection() {
        return area.getSelectedText();
    }

    public void deleteSelection() {
        area.replaceSelection("");
    }

    public void paste(String s) {
        if (!area.getSelectedText().isEmpty()) {
            area.replaceSelection(s);
        } else {
            area.insertText(area.getCaretPosition(), s);
        }
    }
}
