package puho.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class FrancePublicHolidayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrancePublicHolidayService.class);


    List<LocalDate> getPublicHolidaysByYear(final int year) {
        final List<LocalDate> holidaysByYear = new ArrayList<>();
        final LocalDate lundiPaques = getLundiPaques(year);
        final LocalDate ascension = getAscension(lundiPaques);
        final LocalDate pentecote = getPentecote(lundiPaques);
        holidaysByYear.add(getJourAn(year));
        holidaysByYear.add(lundiPaques);
        holidaysByYear.add(getFeteTravail(year));
        holidaysByYear.add(get8Mai(year));
        holidaysByYear.add(ascension);
        holidaysByYear.add(pentecote);
        holidaysByYear.add(getFeteNationale(year));
        holidaysByYear.add(getAssomption(year));
        holidaysByYear.add(getToussaint(year));
        holidaysByYear.add(get11Novembre(year));
        holidaysByYear.add(getNoel(year));
        Collections.sort(holidaysByYear);
        return holidaysByYear;
    }


    //thanks to http://www.aveol.fr/?p=602
    private LocalDate getLundiPaques(final int year) {
        final int a = year / 100;
        final int b = year % 100;
        final int c = (3 * (a + 25)) / 4;
        final int d = (3 * (a + 25)) % 4;
        final int e = (8 * (a + 11)) / 25;
        final int f = (5 * a + b) % 19;
        final int g = (19 * f + c - e) % 30;
        final int h = (f + 11 * g) / 319;
        final int j = (60 * (5 - d) + b) / 4;
        final int k = (60 * (5 - d) + b) % 4;
        final int m = (2 * j - k - g + h) % 7;
        final int n = (g - h + m + 114) / 31;
        final int p = (g - h + m + 114) % 31;
        final int day = p + 1;
        final int month = n;
        final LocalDate dimanchePaques = LocalDate.of(year, month, day);
        return dimanchePaques.plusDays(1L);
    }


    private LocalDate getJourAn(final int year) {
        return LocalDate.of(year, 1, 1);
    }


    private LocalDate getFeteTravail(final int year) {
        return LocalDate.of(year, 5, 1);
    }


    private LocalDate get8Mai(final int year) {
        return LocalDate.of(year, 5, 8);
    }


    private LocalDate getAscension(final LocalDate lundiPaques) {
        return lundiPaques.plusDays(38);
    }


    private LocalDate getPentecote(final LocalDate lundiPaques) {
        return lundiPaques.plusDays(49);
    }


    private LocalDate getFeteNationale(final int year) {
        return LocalDate.of(year, 7, 14);
    }


    private LocalDate getAssomption(final int year) {
        return LocalDate.of(year, 8, 15);
    }


    private LocalDate getToussaint(final int year) {
        return LocalDate.of(year, 11, 1);
    }


    private LocalDate get11Novembre(final int year) {
        return LocalDate.of(year, 11, 11);
    }


    private LocalDate getNoel(final int year) {
        return LocalDate.of(year, 12, 25);
    }
}
