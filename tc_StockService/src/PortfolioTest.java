import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manib on 4/7/18.
 */
public class PortfolioTest {
    Portfolio portfolio ;
    StockService stockService;

    @BeforeMethod
    public void setup() {
        stockService = Mockito.mock(StockService.class);
        portfolio = new Portfolio();
        portfolio.setStockService(stockService);
    }

    @Test
    public void getMarketValueTest() {
        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);
        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        Mockito.when(stockService.getPrice(googleStock)).thenReturn(50.00);
        Mockito.when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        double marketValue = portfolio.getMarketValue();
        Assert.assertEquals(marketValue, 100500.0);
    }


}
