package puho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service(AbstractBetweenStrategy.PREFIX + "FR")
public class FrancePublicHolidayStrategy extends AbstractBetweenStrategy {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FrancePublicHolidayStrategy.class);

    @Autowired
    private FrancePublicHolidayService francePublicHolidayService;


    @Override
    @Cacheable("PublicHolidaysByYear")
    public List<LocalDate> getPublicHolidaysByYear(final int year) {
        LOGGER.debug("Get France Public Holidays for year {}", year);
        return francePublicHolidayService.getPublicHolidayByYear(year);
    }
}
