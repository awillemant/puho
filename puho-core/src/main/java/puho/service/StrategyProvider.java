package puho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import puho.exception.NotSupportedCountryException;

import java.util.Map;

@Service
public class StrategyProvider {

    @Autowired
    private Map<String, AbstractBetweenStrategy> strategies;


    public AbstractBetweenStrategy getByCountryCode(final String countryCode) throws NotSupportedCountryException {
        final String serviceId = AbstractBetweenStrategy.PREFIX + countryCode;
        if (strategies.containsKey(serviceId)) {
            return strategies.get(serviceId);
        } else {
            throw new NotSupportedCountryException(countryCode);
        }
    }
}
