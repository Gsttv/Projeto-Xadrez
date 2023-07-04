package xadrez;

import jogo.GameException;

public class XadrezException extends GameException {
    private static final long serialVersionUID = 1;

    public XadrezException(String message) {
        super(message);
    }
}
