package memento;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Stack;

public class Caretaker{
    private Stack<Memento> _history = new Stack<>();
    private SimpleBooleanProperty _isEmpty = new SimpleBooleanProperty();

    public void undo(){
        if(!_history.isEmpty()) {
            Memento previous = _history.pop();
            previous.restore();
            _isEmpty.set(_history.isEmpty());
        }
    }

    public void push(Memento m){
        _history.push(m);
        _isEmpty.set(_history.isEmpty());
    }

    public ReadOnlyBooleanProperty isEmptyBooleanProperty(){
        return _isEmpty;
    }
}
