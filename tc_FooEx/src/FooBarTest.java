import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by manib on 4/7/18.
 */
public class FooBarTest {

    private Foo foo;

    @BeforeMethod
    public void setupMock() {
        foo = Mockito.mock(Foo.class);
        Mockito.when(foo.greet()).thenReturn(Foo.hello_word);
    }

    @Test
    public void fooGreets() {
        System.out.println("Foo Greets" + foo.greet());
        Assert.assertEquals(Foo.hello_word, foo.greet());
    }

    @Test
    public void barGreets() {
        Bar bar = new Bar();
        Assert.assertEquals(Foo.hello_word, bar.greet(foo));
    }

    @Test (expectedExceptions = FooNotAvailable.class)
    public void fooNotAvailable() {
        Bar bar = new Bar();
        Mockito.when(foo.greet()).thenReturn(null);
        bar.question(foo, "not question");
    }

    @Test
    public void invalidQuestionTest() {
        Bar bar = new Bar();
        String invalidQuestion = "Invalid Question";
        bar.question(foo, invalidQuestion);
        Mockito.verify(foo, Mockito.never()).question(invalidQuestion);
    }

    @Test(expectedExceptions = InvalidQuestion.class)
    public void throwInvalidQuestionExceptionTest() throws InvalidQuestion {
        Bar bar = new Bar();
        String invalidQuestion = "InvaidQuestion";
        Mockito.when(foo.questionStrictly(invalidQuestion)).thenThrow(new InvalidQuestion());
        bar.questionStrictly(foo, invalidQuestion);
    }


}
