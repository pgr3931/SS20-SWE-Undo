import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import memento.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.TextEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class MainController implements Initializable, Originator {
    private Caretaker caretaker;
    private SimpleStringProperty _clipBoard;

    public void cut() {
        save();
        String selectedText = textArea.getSelectedText();
        _clipBoard.set(selectedText);
        textArea.deleteText(textArea.getSelection());
    }

    public void paste() {
        if(!_clipBoard.get().equals("")) {
            save();
            textArea.insertText(textArea.getCaretPosition(), _clipBoard.getValue());
        }
    }

    public void delete() {
        save();
        textArea.deleteText(textArea.getSelection());
    }

    public void type(KeyEvent e){
        save();
        if(!e.getCode().isArrowKey()) {
            //reset selection
            textArea.positionCaret(textArea.getCaretPosition());
            e.consume();
        }

    }

    public void undo() {
        caretaker.undo();
    }

    public TextArea textArea;
    public Button cutBtn;
    public Button pasteBtn;
    public Button deleteBtn;
    public Button undoBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        caretaker = new Caretaker();
        _clipBoard = new SimpleStringProperty();

        // catch key strokes
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, this::type);

        undoBtn.disableProperty().bind(caretaker.isEmptyBooleanProperty());
        cutBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        deleteBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        pasteBtn.disableProperty().bind(Bindings.isEmpty(_clipBoard));
    }

    @Override
    public Memento save() {
        Snapshot s = new Snapshot(this,new State(textArea.getText(),textArea.getCaretPosition()));
        caretaker.push(s);
        return s;
    }

    @Override
    public void setState(State state) {
        //unselect possible selection
        textArea.positionCaret(textArea.getText().length());

        //restore state
        textArea.setText(state.getText());
        textArea.positionCaret(state.getCursorPosition());
    }
}
