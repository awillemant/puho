package puho.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import puho.exception.WrongPeriodException;
import puho.pojo.PublicHoliday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractBetweenStrategy extends AbstractCountryStrategy {

    static final String PREFIX = "publicHolidayBetweenStrategy";

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBetweenStrategy.class);


    protected abstract AbstractByYearStrategy getByYearStrategy();


    @Cacheable("PublicHolidaysBetween")
    public Set<PublicHoliday> getPublicHolidaysBetween(final LocalDate start, final LocalDate end) throws WrongPeriodException {
        LOGGER.debug("Get Public Holidays for country '{}' between {} and {}", getCountryCode(), start, end);
        checkValidity(start, end);
        final Set<PublicHoliday> publicHolidays = new TreeSet<>();
        final List<Integer> years = getAllYearsBetweenDates(start, end);
        if (years.size() == 1) {
            final List<PublicHoliday> holidaysByYear = getByYearStrategy().getPublicHolidaysByYear(years.get(0));
            holidaysByYear.stream()
                    .filter(publicHoliday -> isBetween(publicHoliday.getDate(), start, end))
                    .forEach(publicHolidays::add);
        } else {
            addHolidaysForFirstYear(start, publicHolidays, getByYearStrategy().getPublicHolidaysByYear(years.get(0)));
            for (int i = 1; i < years.size() - 1; i++) {
                publicHolidays.addAll(getByYearStrategy().getPublicHolidaysByYear(years.get(i)));
            }
            addHolidaysForLastYear(end, publicHolidays, getByYearStrategy().getPublicHolidaysByYear(years.get(years.size() - 1)));
        }
        return publicHolidays;
    }


    private void checkValidity(final LocalDate start, final LocalDate end) throws WrongPeriodException {
        if (start.isAfter(end)) {
            throw new WrongPeriodException(start, end);
        }
    }


    private List<Integer> getAllYearsBetweenDates(final LocalDate start, final LocalDate end) {
        final int startYear = start.getYear();
        final int endYear = end.getYear();
        if (startYear == endYear) {
            return Collections.singletonList(startYear);
        } else {
            final List<Integer> years = new ArrayList<>();
            int year = startYear;
            while (year <= endYear) {
                years.add(year);
                year++;
            }
            return years;
        }
    }


    private void addHolidaysForFirstYear(final LocalDate start, final Set<PublicHoliday> publicHolidays, final List<PublicHoliday> holidaysByYear) {
        holidaysByYear.stream()
                .filter(publicHoliday -> isAfterOrEqual(publicHoliday.getDate(), start))
                .forEach(publicHolidays::add);
    }


    private void addHolidaysForLastYear(final LocalDate end, final Set<PublicHoliday> publicHolidays, final List<PublicHoliday> holidaysByYear) {
        holidaysByYear.stream()
                .filter(publicHoliday -> isBeforeOrEqual(publicHoliday.getDate(), end))
                .forEach(publicHolidays::add);
    }


    private boolean isBetween(final LocalDate date, final LocalDate start, final LocalDate end) {
        return isBeforeOrEqual(date, end) && isAfterOrEqual(date, start);
    }


    private boolean isAfterOrEqual(final LocalDate date, final LocalDate start) {
        return date.isAfter(start) || date.isEqual(start);
    }


    private boolean isBeforeOrEqual(final LocalDate date, final LocalDate end) {
        return date.isBefore(end) || date.isEqual(end);
    }
}
