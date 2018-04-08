import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by manib on 4/7/18.
 */

//@RunWith(MockitoJUnitRunner.class)
public class MathAppTester {

    @InjectMocks
    MathApp mathApp = new MathApp();

    @Mock
    CalculatorService calculatorService;

    @BeforeMethod
    public void setup() {
        calculatorService = Mockito.mock(CalculatorService.class);
        mathApp.setCalculatorService(calculatorService);
    }

    @Test
    public void addTest() {


        Mockito.when(calculatorService.add(10, 20)).thenReturn(30.00);
        Assert.assertEquals(mathApp.add(10, 20), 30.00);
        Assert.assertEquals(mathApp.add(10, 20), 30.00);
        Assert.assertEquals(mathApp.add(10, 20), 30.00);

        //Mockito.verify(calculatorService).add(10, 20);
        Mockito.verify(calculatorService, Mockito.times(3)).add(10, 20);
        Mockito.verify(calculatorService, Mockito.times(0)).multiply(10, 20);
        Mockito.verify(calculatorService, Mockito.atMost(3)).add(10, 20);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void addTestException() {

        Mockito.doThrow(new RuntimeException("Add ops not permitted")).when(calculatorService).add(10, 20);
        Assert.assertEquals(mathApp.add(10, 20), 30.00);

    }

    @Test
    public void testAddAndSubtract(){
        Mockito.when(calculatorService.add(10, 20)).thenReturn(30.00);
        Mockito.when(calculatorService.subtract(50, 20)).thenReturn(30.00);

        Assert.assertEquals(mathApp.add(10, 20), 30.00);
        Assert.assertEquals(mathApp.subtract(50, 20), 30.00);

        InOrder inOrder = Mockito.inOrder(calculatorService);
        inOrder.verify(calculatorService).add(10, 20);
        inOrder.verify(calculatorService).subtract(50, 20);
        //inOrder.verify(calculatorService).add(10, 20);
    }

    @Test
    public void testAddAndSubtractTimeout(){
        Mockito.when(calculatorService.add(10, 20)).thenReturn(30.00);
        Mockito.when(calculatorService.subtract(50, 20)).thenReturn(30.00);

        Assert.assertEquals(mathApp.add(10, 20), 30.00);
        Assert.assertEquals(mathApp.subtract(50, 20), 30.00);

        //verify call to add method to be completed within 100 ms
        Mockito.verify(calculatorService, Mockito.timeout(100)).add(10,20);

        //invocation count can be added to ensure multiplication invocations
        //can be checked within given timeframe
        Mockito.verify(calculatorService, Mockito.timeout(100).times(1)).subtract(50,20);
    }

    @Test
    public void testAddAnswerInterface(){

        //add the behavior to add numbers
        Mockito.when(calculatorService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {

            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                //get the arguments passed to mock
                Object[] args = invocation.getArguments();

                //get the mock
                Object mock = invocation.getMock();

                //return the result
                return 30.0;
            }
        });

        //test the add functionality
        Assert.assertEquals(mathApp.add(20.0, 10.0),30.0,0);
    }

    @Test
    public void testAddGiveWhenThen() {
        BDDMockito.given(calculatorService.add(10, 20)).willReturn(30.00);

        Double result = calculatorService.add(10, 20);

        Assert.assertEquals(result, 30.0);
    }



}
