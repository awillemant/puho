package puho.exception;

public class NotSupportedCountryException extends PuhoException {

    public NotSupportedCountryException(final String countryISOCode) {
        super("The ISO code '" + countryISOCode + "' is not recognized !");
    }
}
