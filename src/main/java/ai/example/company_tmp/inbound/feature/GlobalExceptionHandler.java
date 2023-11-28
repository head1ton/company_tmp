package ai.example.company_tmp.inbound.feature;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LPNBarcodeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleLPNBarcodeAlreadyExistsException(
        final LPNBarcodeAlreadyExistsException exception
    ) {
        return new ErrorResponse(exception.getMessage());
    }

    private record ErrorResponse(String message) {

    }
}
