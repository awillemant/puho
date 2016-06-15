package puho.exception;

public class NotSupportedCountryException extends Exception {

    public NotSupportedCountryException(final String countryISOCode) {
        super("The ISO code '" + countryISOCode + "' is not recognized !");
    }
}
