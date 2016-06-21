package puho.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
        final List<LocalDate> puhos = strategy.getInternalPublicHolidaysByYear(2016);
        //THEN
        assertThat(puhos).containsExactly(
                LocalDate.of(2016,1,1),
                LocalDate.of(2016,3,28),
                LocalDate.of(2016,5,1),
                LocalDate.of(2016,5,5),
                LocalDate.of(2016,5,8),
                LocalDate.of(2016,5,16),
                LocalDate.of(2016,7,14),
                LocalDate.of(2016,8,15),
                LocalDate.of(2016,11,1),
                LocalDate.of(2016,11,11),
                LocalDate.of(2016,12,25)
        );
    }
}
