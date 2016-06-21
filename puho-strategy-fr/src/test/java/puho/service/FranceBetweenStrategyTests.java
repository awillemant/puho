package puho.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import puho.test.tooling.TestUtils;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class FranceBetweenStrategyTests {

    private FranceBetweenStrategy strategy = new FranceBetweenStrategy();


    @Before
    public void init() throws Exception {
        TestUtils.injectField(strategy, "franceByYearStrategy", new FranceByYearStrategy());
    }


    @Test
    public void shouldReturnCorrectCountryCode() throws Exception {
        //GIVEN
        //WHEN
        final String countryCode = strategy.getCountryCode();
        //THEN
        assertThat(countryCode).isEqualTo(FranceBetweenStrategy.COUNTRY_CODE);
    }


    @Test
    public void shouldReturnCorrectByYearStrategy() throws Exception {
        //GIVEN
        //WHEN
        final AbstractByYearStrategy byYearStrategy = strategy.getByYearStrategy();
        //THEN
        assertThat(byYearStrategy).isInstanceOf(FranceByYearStrategy.class);
    }
}
