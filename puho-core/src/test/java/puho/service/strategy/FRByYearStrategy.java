package puho.service.strategy;

import puho.pojo.PublicHoliday;
import puho.service.AbstractByYearStrategy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FRByYearStrategy extends AbstractByYearStrategy {

    @Override protected List<PublicHoliday> getInternalPublicHolidaysByYear(final int year) {
        return Arrays.asList(new PublicHoliday("Jour de l'an",LocalDate.of(year, 1, 1)), new PublicHoliday("Fête",LocalDate.of(year, 6, 12)), new PublicHoliday("Noël",(LocalDate.of(year, 12, 25))));
    }


    @Override protected String getCountryCode() {
        return "FR";
    }
}
