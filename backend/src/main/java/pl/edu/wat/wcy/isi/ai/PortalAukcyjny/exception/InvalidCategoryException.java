package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException() {
        super();
    }

    public InvalidCategoryException(String message) {
        super(message);
    }

    public InvalidCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCategoryException(Throwable cause) {
        super(cause);
    }

    protected InvalidCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
