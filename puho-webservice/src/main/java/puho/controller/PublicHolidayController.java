package puho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puho.exception.WrongPeriodException;
import puho.service.StrategyProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RestController
public class PublicHolidayController {

    @Autowired
    private StrategyProvider strategyProvider;


    @RequestMapping("/{countryISOcode:[A-Z]{2}}")
    public ResponseEntity<String> testIfCountryIsSupported(@PathVariable("countryISOcode") final String countryISOCode) throws Exception {
        strategyProvider.getBetweenStrategyByCountryCode(countryISOCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    @RequestMapping("/{countryISOcode:[A-Z]{2}}/{year:\\d{4}}")
    public List<LocalDate> getPublicHolidayForOneSpecificYear(@PathVariable("countryISOcode") final String countryISOCode, @PathVariable("year") final int year) throws Exception {
        return strategyProvider.getByYearStrategyByCountryCode(countryISOCode).getPublicHolidaysByYear(year);
    }


    @RequestMapping("/{countryISOcode:[A-Z]{2}}/{start:\\d{8}}/{end:\\d{8}}")
    public Set<LocalDate> getPublicHolidayBetweenTwoDates(@PathVariable("countryISOcode") final String countryISOCode, @PathVariable("start") final String start, @PathVariable("end") final String end)
            throws Exception, WrongPeriodException {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        final LocalDate startDate = LocalDate.parse(start, dateTimeFormatter);
        final LocalDate endDate = LocalDate.parse(end, dateTimeFormatter);
        return strategyProvider.getBetweenStrategyByCountryCode(countryISOCode).getPublicHolidaysBetween(startDate, endDate);
    }
}
