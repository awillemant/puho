package puho.test.tooling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnit4.class)
public class TestUtilsTests {

    @Test
    public void should() throws Exception {
        //GIVEN
        final ASimplePojo pojo = new ASimplePojo();
        //WHEN
        TestUtils.injectField(pojo, "aPrivateField", "stringValue");
        //THEN
        assertThat(pojo.getAPrivateField()).isEqualTo("stringValue");
    }

    private class ASimplePojo {

        private String aPrivateField;


        String getAPrivateField() {
            return aPrivateField;
        }
    }

    @Test
    public void shouldGetExceptionWhenInstantiating() throws Exception{
        //GIVEN
        final Constructor<TestUtils> constructor = TestUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try{
            //WHEN
            constructor.newInstance();
            fail("Exception not thrown");
        }catch(final Exception exception){
            //THEN
            assertThat(exception).isInstanceOf(InvocationTargetException.class);
            assertThat(((InvocationTargetException) exception).getTargetException()).isInstanceOf(IllegalAccessException.class);
            assertThat(((InvocationTargetException) exception).getTargetException()).hasMessage("Should not be instanciated");
        }

    }
}