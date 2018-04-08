import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by manib on 4/7/18.
 */
public class DemoExTest {

    @Test
    public void doSomethingTest() {
        DemoEx obj = Mockito.mock(DemoEx.class);

        Mockito.doThrow(new IllegalArgumentException()).when(obj).doSomething(true);
        try {
            obj.doSomething(true);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            //do nothing.
        }
    }

}
