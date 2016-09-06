package puho.service.strategy;

import puho.pojo.PublicHoliday;
import puho.service.AbstractByYearStrategy;

import java.util.List;

public class GBByYearStrategy extends AbstractByYearStrategy{

    @Override protected List<PublicHoliday> getInternalPublicHolidaysByYear(final int year) {
        return null;
    }


    @Override protected String getCountryCode() {
        return "GB";
    }
}
