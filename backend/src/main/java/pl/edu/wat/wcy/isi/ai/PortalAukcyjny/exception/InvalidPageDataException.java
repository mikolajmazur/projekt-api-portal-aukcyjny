package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception;

public class InvalidPageDataException extends RuntimeException {
    public InvalidPageDataException() {
    }

    public InvalidPageDataException(String message) {
        super(message);
    }

    public InvalidPageDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPageDataException(Throwable cause) {
        super(cause);
    }

    public InvalidPageDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
