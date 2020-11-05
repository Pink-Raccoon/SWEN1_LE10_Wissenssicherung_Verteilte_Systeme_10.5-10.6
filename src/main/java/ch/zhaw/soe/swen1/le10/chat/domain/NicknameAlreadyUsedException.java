package ch.zhaw.soe.swen1.le10.chat.domain;

/**
 * Thrown if a nickname is already used by another chat client.
 */
public class NicknameAlreadyUsedException extends Exception {
    private static final long serialVersionUID = 2853090059751339801L;

    public NicknameAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }
}