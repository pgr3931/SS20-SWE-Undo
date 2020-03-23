import command.pattern.*;
import command.pattern.commands.*;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private Text text;
    private CommandInvoker invoker;

    public void cut() {
        invoker.execute(new CutCommand(text, invoker));
    }

    public void paste() {
        invoker.execute(new PasteCommand(text, invoker));
    }

    public void delete() {
        invoker.execute(new DeleteCommand(text, invoker));
    }

    public void undo() {
        invoker.undo();
    }


    public TextArea textArea;
    public Button cutBtn;
    public Button pasteBtn;
    public Button deleteBtn;
    public Button undoBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text = new Text(textArea);
        invoker = new CommandInvoker();

        // Overwrite default text processing
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(!e.getCode().isArrowKey()) {
                e.consume();
                invoker.execute(new TypeCommand(text, invoker, textArea.getCaretPosition(), e.isShiftDown(), e.getCode()));
            }
        });

        undoBtn.disableProperty().bind(Bindings.size(invoker.getHistory()).isEqualTo(0));
        cutBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        deleteBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        pasteBtn.disableProperty().bind(Bindings.isEmpty(invoker.clipBoardProperty()));
    }
}
