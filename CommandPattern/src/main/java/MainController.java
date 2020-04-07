import command.pattern.CommandInvoker;
import command.pattern.OperationReceiver;
import command.pattern.commands.*;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Client class
 */
public class MainController implements Initializable {
    private OperationReceiver operationReceiver;
    private CommandInvoker invoker;

    public void cut() {
        invoker.execute(new CutCommand(operationReceiver, invoker));
    }

    public void paste() {
        invoker.execute(new PasteCommand(operationReceiver, invoker));
    }

    public void delete() {
        invoker.execute(new DeleteCommand(operationReceiver, invoker));
    }

    public void undo() {
        invoker.undo();
    }

    public void redo() {
        invoker.redo();
    }

    public TextArea textArea;
    public ListView<Command> historyList;
    public Button cutBtn;
    public Button pasteBtn;
    public Button deleteBtn;
    public Button undoBtn;
    public Button redoBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operationReceiver = new OperationReceiver(textArea);
        invoker = new CommandInvoker();
        historyList.setItems(invoker.getHistory());

        // Overwrite default text processing
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (!e.getCode().isArrowKey()) {
                e.consume();
                invoker.execute(new TypeCommand(operationReceiver, invoker, textArea.getCaretPosition(), e.isShiftDown(), e.getCode()));
            }
        });

        textArea.addEventFilter(KeyEvent.KEY_TYPED, Event::consume);

        historyList.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        invoker.getIndex().addListener((obs, oldValue, newValue) -> {
            historyList.getSelectionModel().select(newValue.intValue());
            // JavaFx is way too buggy... ignore the exception
            historyList.scrollTo(newValue.intValue());
        });

        undoBtn.disableProperty().bind(Bindings.isEmpty(invoker.getHistory()).or(invoker.getIndex().isEqualTo(-1)));
        redoBtn.disableProperty().bind(Bindings.isEmpty(invoker.getHistory()).or(invoker.getIndex().isEqualTo(Bindings.size(invoker.getHistory()).subtract(1))));
        cutBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        deleteBtn.disableProperty().bind(Bindings.isEmpty(textArea.selectedTextProperty()));
        pasteBtn.disableProperty().bind(Bindings.isEmpty(invoker.clipBoardProperty()));
    }


}
