import command.pattern.CommandInvoker;
import command.pattern.Text;
import command.pattern.commands.CutCommand;
import command.pattern.commands.DeleteCommand;
import command.pattern.commands.PasteCommand;
import command.pattern.commands.UndoCommand;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TextArea textArea;
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
        invoker.execute(new UndoCommand(text, invoker));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text = new Text(textArea);
        invoker = new CommandInvoker();
    }
}
