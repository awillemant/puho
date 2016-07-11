package puho.exception;

import java.time.LocalDate;

public class WrongPeriodException extends PuhoException {

    public WrongPeriodException(final LocalDate start, final LocalDate end) {
        super(start + " is after " + end);
    }
}
