package puho.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import puho.pojo.PublicHoliday;
import puho.service.strategy.FRByYearStrategy;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class ByYearStrategyTests {


    @Test
    public void shouldReturnInternalImplementation() throws Exception {
        //GIVEN
        AbstractByYearStrategy strategy = new FRByYearStrategy();
        //WHEN
        List<PublicHoliday> puhos = strategy.getPublicHolidaysByYear(1983);
        //THEN
        assertThat(puhos).extracting("date").containsExactly(LocalDate.of(1983,1,1),LocalDate.of(1983,6,12),LocalDate.of(1983,12,25));
    }

}