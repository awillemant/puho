package puho.test.tooling;

import java.lang.reflect.Field;

public final class TestUtils {

    private TestUtils()  {
        throw new RuntimeException("Should not be instanciated");
    }


    public static void injectField(final Object recipient, final String fieldName, final Object fieldValue) throws IllegalAccessException, NoSuchFieldException {
        final Field field = recipient.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(recipient, fieldValue);
    }
}
