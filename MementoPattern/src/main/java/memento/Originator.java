package memento;

public interface Originator {
    Memento save();
    void setState(State state);
}
