package puho.test;

import java.lang.reflect.Field;

public class TestUtils {

    public static void injectField(final Object recipient, final String fieldName, final Object fieldValue) throws IllegalAccessException, NoSuchFieldException {
        Field field = recipient.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(recipient,fieldValue);
    }
}
