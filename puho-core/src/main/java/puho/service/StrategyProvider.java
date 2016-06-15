package puho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puho.exception.NotSupportedCountryException;

import java.util.Map;

@Service
public class StrategyProvider {

    @Autowired
    private Map<String, AbstractBetweenStrategy> betweenStrategies;

    @Autowired
    private Map<String, AbstractByYearStrategy> byYearStrategies;


    public AbstractBetweenStrategy getBetweenStrategyByCountryCode(final String countryCode) throws NotSupportedCountryException {
        final String serviceId = AbstractBetweenStrategy.PREFIX + countryCode;
        if (betweenStrategies.containsKey(serviceId)) {
            return betweenStrategies.get(serviceId);
        } else {
            throw new NotSupportedCountryException(countryCode);
        }
    }

    public AbstractByYearStrategy getByYearStrategyByCountryCode(final String countryCode) throws NotSupportedCountryException {
        final String serviceId = AbstractByYearStrategy.PREFIX + countryCode;
        if (byYearStrategies.containsKey(serviceId)) {
            return byYearStrategies.get(serviceId);
        } else {
            throw new NotSupportedCountryException(countryCode);
        }
    }
}
