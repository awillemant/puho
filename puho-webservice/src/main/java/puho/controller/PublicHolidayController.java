package puho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puho.exception.NotSupportedCountryException;
import puho.exception.PuhoException;
import puho.pojo.PublicHoliday;
import puho.service.StrategyProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/{countryISOcode:[A-Z]{2}}")
public class PublicHolidayController {

    @Autowired
    private StrategyProvider strategyProvider;


    @RequestMapping("")
    public ResponseEntity<String> testIfCountryIsSupported(@PathVariable("countryISOcode") final String countryISOCode) throws NotSupportedCountryException {
        strategyProvider.getBetweenStrategyByCountryCode(countryISOCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    @RequestMapping("/{year:\\d{4}}")
    public List<PublicHoliday> getPublicHolidayForOneSpecificYear(@PathVariable("countryISOcode") final String countryISOCode, @PathVariable("year") final int year) throws NotSupportedCountryException {
        return strategyProvider.getByYearStrategyByCountryCode(countryISOCode).getPublicHolidaysByYear(year);
    }


    @RequestMapping("/{start:\\d{8}}/{end:\\d{8}}")
    public Set<PublicHoliday> getPublicHolidayBetweenTwoDates(@PathVariable("countryISOcode") final String countryISOCode, @PathVariable("start") final String start, @PathVariable("end") final String end)
            throws PuhoException {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        final LocalDate startDate = LocalDate.parse(start, dateTimeFormatter);
        final LocalDate endDate = LocalDate.parse(end, dateTimeFormatter);
        return strategyProvider.getBetweenStrategyByCountryCode(countryISOCode).getPublicHolidaysBetween(startDate, endDate);
    }
}
