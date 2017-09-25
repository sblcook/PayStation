package paystation.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RateStrategyImpl implements RateStrategy{

    private RateStrategy rateStrategy = new LinearRateStrategy(); //default to linear rate strategy
    private double timeBought;

    @Override
    public double calculateTime(int insertedSoFar) {
        return timeBought = rateStrategy.calculateTime(insertedSoFar);
    }
    public void changeRateStrategy(RateStrategy newRateStrategy){
        rateStrategy = newRateStrategy;
    }
}
