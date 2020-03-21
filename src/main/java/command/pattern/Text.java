package command.pattern;

import javafx.scene.control.TextArea;

/**
 * Receiver class
 */
public class Text {
    private TextArea area;

    public Text(TextArea area){
        this.area = area;
    }

    public String getText(){
        return area.getText();
    }

    public void setText(String t){
        area.setText(t);
    }

    public String getSelection(){
        return area.getSelectedText();
    }

    public void deleteSelection(){
        area.replaceSelection("");
    }

    public void paste(){
        area.paste();
    }
}
