package paystation.domain;

public class RateStrategyImpl implements RateStrategy{

    private String rateStrategy = linear; //default to linear rate strategy
    private double timeBought;

    @Override
    public double calculateTime(int insertedSoFar){
        switch (rateStrategy) {
            case linear:
                timeBought = calculateLinear(insertedSoFar);
                break;
            case progressive:
                timeBought = calculateProgressive(insertedSoFar);
                break;
            case alternating:
                timeBought = calculateAlternating(insertedSoFar);
                break;
            default:
                timeBought = insertedSoFar;
                break;
        }
        return timeBought;
    }

    @Override
    public double calculateLinear(int insertedSoFar){
        return insertedSoFar / 5 * 2;
    }

    @Override
    public double calculateProgressive(int insertedSoFar){

        if(insertedSoFar < 150){ //first hour
            timeBought = calculateLinear(insertedSoFar);
        }
        else if (insertedSoFar > 350){//second hour
            timeBought = insertedSoFar / 5 * 1.5;
        }
        else { //third hour and beyond
            timeBought = insertedSoFar / 5;
        }
        return timeBought;
    }

    @Override
    public double calculateAlternating(int insertedSoFar){
        //math goes here
        return 0;
    }

    @Override
    public void changeRateStrategy(String newRateStrategy){
        if(newRateStrategy.equalsIgnoreCase(linear))
            rateStrategy = linear;
        else if(newRateStrategy.equalsIgnoreCase(progressive))
            rateStrategy = progressive;
        else if(newRateStrategy.equalsIgnoreCase(alternating))
            rateStrategy = alternating;
    }
}
