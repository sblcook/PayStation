package paystation.domain;

public class RateStrategyImpl implements RateStrategy{

    private String rateStrategy = "Linear";

    @Override
    public int calculateTime(int insertedSoFar){
        switch (rateStrategy) {
            case "Linear":
                return insertedSoFar / 5 * 2;
        }
        return 0;
    }

    @Override
    public void changeRateStrategy(){

    }
}
