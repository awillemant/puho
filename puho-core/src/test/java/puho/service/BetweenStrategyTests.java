package puho.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import puho.exception.WrongPeriodException;
import puho.service.strategy.FRBetweenStrategy;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class BetweenStrategyTests {

    @Test
    public void shouldGetWrontPeriodException() throws Exception {
        //GIVEN
        AbstractBetweenStrategy strategy = new FRBetweenStrategy();
        try{
            //WHEN
            strategy.getPublicHolidaysBetween(LocalDate.of(2016,12,1),LocalDate.of(2016,11,1));
            fail("Exception not thrown");
        }catch(final Exception exception){
            //THEN
            assertThat(exception).isInstanceOf(WrongPeriodException.class);
            assertThat(exception).hasMessage(LocalDate.of(2016,12,1).toString() + " is after " + LocalDate.of(2016,11,1).toString());
        }
    }

    @Test
    public void shouldReturnOnlyTwoLastPuhosOfYear() throws Exception {
        //GIVEN
        final int year = 2016;
        AbstractBetweenStrategy strategy = new FRBetweenStrategy();
        //WHEN
        final Set<LocalDate> publicHolidaysBetween = strategy.getPublicHolidaysBetween(LocalDate.of(year, 3, 1), LocalDate.of(year, 12, 31));
        //THEN
        assertThat(publicHolidaysBetween).containsExactly(LocalDate.of(year, 6, 12), LocalDate.of(year, 12, 25));
    }

    @Test
    public void shouldReturnAllPuhosOf2Years() throws Exception {
        //GIVEN
        AbstractBetweenStrategy strategy = new FRBetweenStrategy();
        //WHEN
        final Set<LocalDate> publicHolidaysBetween = strategy.getPublicHolidaysBetween(LocalDate.of(2015, 1, 1), LocalDate.of(2016, 12, 31));
        //THEN
        assertThat(publicHolidaysBetween).containsExactly(
                LocalDate.of(2015, 1,1),
                LocalDate.of(2015, 6,12),
                LocalDate.of(2015, 12, 25),
                LocalDate.of(2016, 1,1),
                LocalDate.of(2016, 6,12),
                LocalDate.of(2016, 12, 25));
    }
    @Test
    public void shouldReturnAllPuhosOf2YearsAndAHalf() throws Exception {
        //GIVEN
        AbstractBetweenStrategy strategy = new FRBetweenStrategy();
        //WHEN
        final Set<LocalDate> publicHolidaysBetween = strategy.getPublicHolidaysBetween(LocalDate.of(2015, 1, 1), LocalDate.of(2017, 6, 15));
        //THEN
        assertThat(publicHolidaysBetween).containsExactly(
                LocalDate.of(2015, 1,1),
                LocalDate.of(2015, 6,12),
                LocalDate.of(2015, 12, 25),
                LocalDate.of(2016, 1,1),
                LocalDate.of(2016, 6,12),
                LocalDate.of(2016, 12, 25),
                LocalDate.of(2017, 1,1),
                LocalDate.of(2017, 6,12));
    }
}