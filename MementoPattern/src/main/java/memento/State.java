package memento;

public class State {
    private String _text;
    private int _cursorPosition;

    public State(String text, int cursorPosition){
        _text = text;
        _cursorPosition = cursorPosition;
    }

    public String getText(){
        return _text;
    }
    public int getCursorPosition() { return _cursorPosition;}
}
