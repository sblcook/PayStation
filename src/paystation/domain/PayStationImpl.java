package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private double timeBought;
    private Map<Integer, Integer> coinValues = new HashMap<>();
    RateStrategyImpl rateStrategy = new RateStrategyImpl();

    public static void main(String args[]){

    }

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                int fiveCount = coinValues.containsKey(5) ? coinValues.get(5) : 0;
                coinValues.put(5, fiveCount + 1);
                break;
            case 10:
                int tenCount = coinValues.containsKey(10) ? coinValues.get(10) : 0;
                coinValues.put(10, tenCount + 1);
                break;
            case 25:
                int quarterCount = coinValues.containsKey(25) ? coinValues.get(25) : 0;
                coinValues.put(25, quarterCount + 1);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;

        timeBought = rateStrategy.calculateTime(insertedSoFar);
    }

    @Override
    public double readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        clearCoinValuesMap();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        reset();

        Map returnCoinValues = new HashMap();
        if (coinValues.containsKey(5))
            returnCoinValues.put(5, coinValues.get(5));
        if (coinValues.containsKey(10))
            returnCoinValues.put(10, coinValues.get(10));
        if (coinValues.containsKey(25))
            returnCoinValues.put(25, coinValues.get(25));

        clearCoinValuesMap();

        return returnCoinValues;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
    }

    @Override
    public int empty() {
        int tempInsertedSoFar = insertedSoFar;
        insertedSoFar = 0;
        timeBought = 0;
        return tempInsertedSoFar;
    }

    public void clearCoinValuesMap() {
        coinValues.put(5, 0);
        coinValues.put(10, 0);
        coinValues.put(25, 0);
    }


    public void changeRateStrategy(RateStrategy newRateStrategy){
        rateStrategy.changeRateStrategy(newRateStrategy);
    }
}
