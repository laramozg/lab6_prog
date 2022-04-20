package server;

import java.io.Serializable;

public enum AnswerType implements Serializable {
    NEED_ELEMENT,
    RETURN_ACTION,
    GETTER_STATE,
    DIALOG_STATE,
    EXIT
}