import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by manib on 4/7/18.
 */
public class FooTest {

    @Test
    public void fooGreets() {
        Foo foo = Mockito.mock(Foo.class);
        Mockito.when(foo.greet()).thenReturn(Foo.hello_word);
        System.out.println("Foo Greets: " + foo.greet());
        Assert.assertEquals(foo.greet(), Foo.hello_word);

    }

}
