package paystation.domain;

public class RateStrategyImpl implements RateStrategy{

    private String rateStrategy = linear; //default to linear rate strategy
    private double timeBought;

    @Override
    public double calculateTime(int insertedSoFar){
        switch (rateStrategy) {
            case linear:
                timeBought = insertedSoFar / 5 * 2;
                break;
            case progressive:
                if(insertedSoFar < 150){ //first hour
                    timeBought = insertedSoFar / 5 * 2;
                }
                else if (insertedSoFar > 350){//second hour
                    timeBought = insertedSoFar / 5 * 1.5;
                }
                else { //third hour and beyond

                }
                break;
            case alternating:

                break;
            default:
                timeBought = 0;
                break;
        }
        return timeBought;
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
