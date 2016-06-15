package puho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service(AbstractByYearStrategy.PREFIX + FranceByYearStrategy.COUNTRY_CODE) class FranceByYearStrategy extends AbstractByYearStrategy {

    static final String COUNTRY_CODE = "FR";

    @Autowired
    private FrancePublicHolidayService francePublicHolidayService;


    @Override
    protected List<LocalDate> getInternalPublicHolidaysByYear(final int year) {
        return francePublicHolidayService.getPublicHolidaysByYear(year);
    }


    @Override protected String getCountryCode() {
        return COUNTRY_CODE;
    }
}
