package com.lsy.exception;

/**
 * @author xinhuiyang
 * @date 2019-09-02
 */
public class DataCenterRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 6270759725472490774L;


    public DataCenterRuntimeException() {
        this(null, null);
    }

    public DataCenterRuntimeException(String message, Throwable cause) {
        super(message, cause, true, false);
    }

    public DataCenterRuntimeException(String message) {
        this(message, null);
    }

    public DataCenterRuntimeException(Throwable cause) {
        this(null, cause);
    }
}
