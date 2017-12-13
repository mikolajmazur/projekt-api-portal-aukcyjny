package pl.edu.wat.wcy.isi.ai.PortalAukcyjny.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Auction not found")
public class AuctionNotFoundException extends RuntimeException {
    public AuctionNotFoundException() {
        super();
    }

    public AuctionNotFoundException(String message) {
        super(message);
    }

    public AuctionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AuctionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
