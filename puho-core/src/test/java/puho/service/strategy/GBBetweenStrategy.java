package puho.service.strategy;

import puho.service.AbstractBetweenStrategy;
import puho.service.AbstractByYearStrategy;

public class GBBetweenStrategy extends AbstractBetweenStrategy {

    @Override protected AbstractByYearStrategy getByYearStrategy() {
        return null;
    }


    @Override protected String getCountryCode() {
        return "GB";
    }
}
