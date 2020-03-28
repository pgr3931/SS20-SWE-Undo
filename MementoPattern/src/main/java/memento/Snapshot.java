package memento;

/**
 * Concrete implementation of the Memento interface
 */
public class Snapshot implements Memento {
    private Originator _originator;
    private State _state;

    public Snapshot(Originator originator, State state){
        _originator = originator;
        _state = state;
    }

    @Override
    public void restore() {
        _originator.setState(_state);
    }
}
