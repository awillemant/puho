package puho.test.tooling;

import java.lang.reflect.Field;

public final class TestUtils {

    private TestUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Should not be instanciated");
    }


    public static void injectField(final Object recipient, final String fieldName, final Object fieldValue) throws ReflectiveOperationException {
        final Field field = recipient.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(recipient, fieldValue);
    }
}
