package puho.service.strategy;

import puho.service.AbstractByYearStrategy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FRByYearStrategy extends AbstractByYearStrategy {

    @Override protected List<LocalDate> getInternalPublicHolidaysByYear(final int year) {
        return Arrays.asList(LocalDate.of(year, 1, 1), LocalDate.of(year, 6, 12), (LocalDate.of(year, 12, 25)));
    }


    @Override protected String getCountryCode() {
        return "FR";
    }
}
