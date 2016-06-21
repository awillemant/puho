package puho.service.strategy;

import puho.service.AbstractBetweenStrategy;
import puho.service.AbstractByYearStrategy;

public class FRBetweenStrategy extends AbstractBetweenStrategy {

    @Override protected AbstractByYearStrategy getByYearStrategy() {
        return new FRByYearStrategy();
    }


    @Override protected String getCountryCode() {
        return "FR";
    }
}
