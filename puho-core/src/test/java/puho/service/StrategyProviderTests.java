package puho.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import puho.exception.NotSupportedCountryException;
import puho.service.strategy.FRBetweenStrategy;
import puho.service.strategy.FRByYearStrategy;
import puho.service.strategy.GBBetweenStrategy;
import puho.service.strategy.GBByYearStrategy;
import puho.test.TestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class StrategyProviderTests {

    private StrategyProvider strategyProvider = new StrategyProvider();


    @Before
    public void init() throws Exception {
        Map<String, AbstractByYearStrategy> byYearStrategies = new HashMap<>();
        Map<String, AbstractBetweenStrategy> betweenStrategies = new HashMap<>();
        TestUtils.injectField(strategyProvider, "byYearStrategies", byYearStrategies);
        TestUtils.injectField(strategyProvider, "betweenStrategies", betweenStrategies);
        byYearStrategies.put(AbstractByYearStrategy.PREFIX + "FR", new FRByYearStrategy());
        byYearStrategies.put(AbstractByYearStrategy.PREFIX + "GB", new GBByYearStrategy());
        betweenStrategies.put(AbstractBetweenStrategy.PREFIX + "FR", new FRBetweenStrategy());
        betweenStrategies.put(AbstractBetweenStrategy.PREFIX + "GB", new GBBetweenStrategy());
    }


    @Test
    public void shouldReturnCorrectByYearStrategy() throws Exception {
        //GIVEN
        final String keyFR = "FR";
        final String keyGB = "GB";
        //WHEN
        final AbstractByYearStrategy strategyFR = strategyProvider.getByYearStrategyByCountryCode(keyFR);
        final AbstractByYearStrategy strategyGB = strategyProvider.getByYearStrategyByCountryCode(keyGB);
        //THEN
        assertThat(strategyFR).isInstanceOf(FRByYearStrategy.class);
        assertThat(strategyGB).isInstanceOf(GBByYearStrategy.class);
        assertThat(strategyFR.getCountryCode()).isEqualTo(keyFR);
        assertThat(strategyGB.getCountryCode()).isEqualTo(keyGB);
    }


    @Test
    public void shouldReturnCorrectBetweenStrategy() throws Exception {
        //GIVEN
        final String keyFR = "FR";
        final String keyGB = "GB";
        //WHEN
        final AbstractBetweenStrategy strategyFR = strategyProvider.getBetweenStrategyByCountryCode(keyFR);
        final AbstractBetweenStrategy strategyGB = strategyProvider.getBetweenStrategyByCountryCode(keyGB);
        //THEN
        assertThat(strategyFR).isInstanceOf(FRBetweenStrategy.class);
        assertThat(strategyGB).isInstanceOf(GBBetweenStrategy.class);
        assertThat(strategyFR.getCountryCode()).isEqualTo(keyFR);
        assertThat(strategyGB.getCountryCode()).isEqualTo(keyGB);
    }


    @Test
    public void shouldThrowExceptionWithWrongCountryCodeAndByYearStrategy() throws Exception {
        //GIVEN
        final String key = "US";
        try {
            //WHEN
            strategyProvider.getByYearStrategyByCountryCode(key);
            fail("Exception not thrown");
        } catch (final Exception exception) {
            //THEN
            assertThat(exception).isInstanceOf(NotSupportedCountryException.class);
            assertThat(exception).hasMessage("The ISO code 'US' is not recognized !");
        }
    }


    @Test
    public void shouldThrowExceptionWithWrongCountryCodeAndBetweenStrategy() throws Exception {
        //GIVEN
        final String key = "US";
        try {
            //WHEN
            final AbstractBetweenStrategy strategy = strategyProvider.getBetweenStrategyByCountryCode(key);
            fail("Exception not thrown");
        } catch (final Exception exception) {
            //THEN
            assertThat(exception).isInstanceOf(NotSupportedCountryException.class);
            assertThat(exception).hasMessage("The ISO code 'US' is not recognized !");
        }
    }
}