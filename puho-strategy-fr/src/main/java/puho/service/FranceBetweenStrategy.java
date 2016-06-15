package puho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AbstractBetweenStrategy.PREFIX + FranceBetweenStrategy.COUNTRY_CODE)
public class FranceBetweenStrategy extends AbstractBetweenStrategy {

    static final String COUNTRY_CODE = "FR";

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FranceBetweenStrategy.class);

    @Autowired
    private FranceByYearStrategy franceByYearStrategy;


    @Override protected AbstractByYearStrategy getByYearStrategy() {
        return franceByYearStrategy;
    }


    @Override protected String getCountryCode() {
        return COUNTRY_CODE;
    }
}
