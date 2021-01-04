package yyyq.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @author liyunzhi
 */
public class CustomException extends RuntimeException {

    private final String message;
    private HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message) {
        this.message = message;
        this.httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
