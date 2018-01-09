package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception;

public class RecaptchaInvalidException extends RuntimeException {
    public RecaptchaInvalidException() {
    }

    public RecaptchaInvalidException(String message) {
        super(message);
    }

    public RecaptchaInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecaptchaInvalidException(Throwable cause) {
        super(cause);
    }

    public RecaptchaInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
