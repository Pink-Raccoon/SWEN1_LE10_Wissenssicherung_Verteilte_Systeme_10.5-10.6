package ch.zhaw.soe.swen1.le10.chat.domain;

/**
 * Thrown if a chat listener is not reachable anymore.
 */
public class ListenerNotReachableException extends Exception {
    private static final long serialVersionUID = 1L;

    public ListenerNotReachableException(String errorMessage) {
        super(errorMessage);
    }
}