package puho.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import puho.exception.NotSupportedCountryException;
import puho.exception.WrongPeriodException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ NotSupportedCountryException.class, WrongPeriodException.class })
    public String handleException(final Exception ex) {
        return ex.getMessage();
    }
}
