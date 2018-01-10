package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalBidException extends RuntimeException {
    public IllegalBidException() {
    }

    public IllegalBidException(String message) {
        super(message);
    }

    public IllegalBidException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBidException(Throwable cause) {
        super(cause);
    }

    public IllegalBidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
