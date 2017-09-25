package paystation.domain;

public class LinearRateStrategy implements RateStrategy {

    @Override
    public double calculateTime(int insertedSoFar){
        return insertedSoFar / 5 * 2;
    }
}
