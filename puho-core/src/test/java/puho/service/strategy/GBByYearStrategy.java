package puho.service.strategy;

import puho.service.AbstractByYearStrategy;

import java.time.LocalDate;
import java.util.List;

public class GBByYearStrategy extends AbstractByYearStrategy{

    @Override protected List<LocalDate> getInternalPublicHolidaysByYear(final int year) {
        return null;
    }


    @Override protected String getCountryCode() {
        return "GB";
    }
}
