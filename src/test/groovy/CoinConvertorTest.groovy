package  src.test.groovy

import com.coinconvertor.CoinConvertor
import org.junit.Before
import org.junit.Test
import org.junit.Assert

class CoinConvertorTest {

    def coinConvertor;

    @Before
    void setup()
    {
        coinConvertor = new CoinConvertor();
        CoinConvertor.loadRupeeDenomination();
        CoinConvertor.loadDollarDenomination();
        CoinConvertor.loadEuroDenomination();

    }

    @Test
    void 'assert equals'(){
        Assert.assertEquals(' 1 Two Rupees Coin(s).',coinConvertor.convertToCoins("R2.0"))
    }

    @Test
    void 'Euros conversion for 0.87'(){
        Assert.assertEquals(' 1 Fifty Cents, 1 Twenty Cents, 1 Ten Cents, 1 Five Cents, 1 Two Cents, Coin(s).',coinConvertor.convertToCoins("E0.87"))
    }

    @Test
    void 'Rupee conversion for 0.87'(){
        Assert.assertEquals(' 1 Fifty Paise, 1 Twenty Five Paise, 1 Ten Paise, 2 One Paise, Coin(s).',coinConvertor.convertToCoins("R0.87"))
    }

    @Test
    void 'Dollar conversion for 0.87'(){
        Assert.assertEquals(' 3 Quartor, 1 Dime, 2 Pennies, Coin(s).',coinConvertor.convertToCoins("D0.87"))
    }

    @Test
    void 'Invalid currency check '(){
        Assert.assertEquals('Invalid  Currency type, allowed currencies are Rupee, Dollar and Euro.',coinConvertor.convertToCoins("S0.87"))
    }

    @Test
    void 'If entered currency value is less than or zero '(){
        Assert.assertEquals('Please enter  currency greater than 0.',coinConvertor.convertToCoins("D0"))
    }

    @Test
    void 'Negative value check '(){
        Assert.assertEquals('Please enter  currency greater than 0.',coinConvertor.convertToCoins("D-0.87"))
    }

    @Test
    void 'Input with more points '(){
        Assert.assertEquals('Invalid input with multiple points',coinConvertor.convertToCoins("D-0.87.1"))
    }


}

