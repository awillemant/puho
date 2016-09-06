package puho.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import puho.pojo.PublicHoliday;

import java.util.List;

public abstract class AbstractByYearStrategy extends AbstractCountryStrategy {

    static final String PREFIX = "publicHolidayByYearStrategy";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractByYearStrategy.class);


    protected abstract List<PublicHoliday> getInternalPublicHolidaysByYear(int year);


    @Cacheable("PublicHolidaysByYear")
    public List<PublicHoliday> getPublicHolidaysByYear(final int year) {
        LOGGER.debug("Get Public Holidays for country '{}' and year {}", getCountryCode(), year);
        return getInternalPublicHolidaysByYear(year);
    }
}
