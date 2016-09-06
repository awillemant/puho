package puho.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import puho.pojo.PublicHoliday;
import puho.test.tooling.TestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class FranceByYearStrategyTests {

    private FranceByYearStrategy strategy = new FranceByYearStrategy();


    @Before
    public void init() throws Exception {
        TestUtils.injectField(strategy,"francePublicHolidayService",new FrancePublicHolidayService());
    }


    @Test
    public void shouldReturnCorrectCountryCode() throws Exception {
        //GIVEN
        //WHEN
        final String countryCode = strategy.getCountryCode();
        //THEN
        assertThat(countryCode).isEqualTo(FranceByYearStrategy.COUNTRY_CODE);
    }


    @Test
    public void shouldReturnCorrectPuhos() throws Exception {
        //GIVEN
        //WHEN
        final List<PublicHoliday> puhos = strategy.getInternalPublicHolidaysByYear(2016);
        //THEN
        assertThat(puhos).extracting("name","date").containsExactly(
                tuple("Jour de l'an",LocalDate.of(2016,1,1)),
                tuple("Lundi de Pâques",LocalDate.of(2016,3,28)),
                tuple("Fête du travail",LocalDate.of(2016,5,1)),
                tuple("Ascension",LocalDate.of(2016,5,5)),
                tuple("8 Mai 1945",LocalDate.of(2016,5,8)),
                tuple("Pentecôte",LocalDate.of(2016,5,16)),
                tuple("Fête nationale",LocalDate.of(2016,7,14)),
                tuple("Assomption",LocalDate.of(2016,8,15)),
                tuple("Toussaint",LocalDate.of(2016,11,1)),
                tuple("Armistice",LocalDate.of(2016,11,11)),
                tuple("Noël",LocalDate.of(2016,12,25))
        );
    }
}
