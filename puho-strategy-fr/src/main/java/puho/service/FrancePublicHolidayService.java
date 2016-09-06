package puho.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import puho.pojo.PublicHoliday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
class FrancePublicHolidayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrancePublicHolidayService.class);


    List<PublicHoliday> getPublicHolidaysByYear(final int year) {
        final List<PublicHoliday> holidaysByYear = new ArrayList<>();
        final PublicHoliday lundiPaques = getLundiPaques(year);
        final PublicHoliday ascension = getAscension(lundiPaques.getDate());
        final PublicHoliday pentecote = getPentecote(lundiPaques.getDate());
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
        Collections.sort(holidaysByYear, (ph1, ph2)->ph1.getDate().compareTo(ph2.getDate()));
        return holidaysByYear;
    }


    //thanks to http://www.aveol.fr/?p=602
    private PublicHoliday getLundiPaques(final int year) {
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
        final LocalDate lundiPaques = LocalDate.of(year, month, day).plusDays(1L);
        return new PublicHoliday("Lundi de Pâques",lundiPaques);
    }


    private PublicHoliday getJourAn(final int year) {
        return new PublicHoliday("Jour de l'an",LocalDate.of(year, 1, 1));
    }


    private PublicHoliday getFeteTravail(final int year) {
        return new PublicHoliday("Fête du travail", LocalDate.of(year, 5, 1));
    }


    private PublicHoliday get8Mai(final int year) {
        return new PublicHoliday("8 Mai 1945", LocalDate.of(year, 5, 8));
    }


    private PublicHoliday getAscension(final LocalDate dateLundiPaques) {
        return new PublicHoliday("Ascension", dateLundiPaques.plusDays(38));
    }


    private PublicHoliday getPentecote(final LocalDate dateLundiPaques) {
        return new PublicHoliday("Pentecôte", dateLundiPaques.plusDays(49));
    }


    private PublicHoliday getFeteNationale(final int year) {
        return new PublicHoliday("Fête nationale", LocalDate.of(year, 7, 14));
    }


    private PublicHoliday getAssomption(final int year) {
        return new PublicHoliday("Assomption", LocalDate.of(year, 8, 15));
    }


    private PublicHoliday getToussaint(final int year) {
        return new PublicHoliday("Toussaint", LocalDate.of(year, 11, 1));
    }


    private PublicHoliday get11Novembre(final int year) {
        return new PublicHoliday("Armistice", LocalDate.of(year, 11, 11));
    }


    private PublicHoliday getNoel(final int year) {
        return new PublicHoliday("Noël", LocalDate.of(year, 12, 25));
    }
}
