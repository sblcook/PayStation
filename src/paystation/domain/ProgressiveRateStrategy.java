package paystation.domain;

public class ProgressiveRateStrategy implements RateStrategy {

    @Override
    public double calculateTime(int insertedSoFar){
        double timeBought = 0;
        if(insertedSoFar < 150){ //first hour
            LinearRateStrategy linearRateStrategy = new LinearRateStrategy();
            timeBought = linearRateStrategy.calculateTime(insertedSoFar);
        }
        else if (insertedSoFar > 150){//second hour
            timeBought = insertedSoFar / 5 * 1.5;
        }
        else if (insertedSoFar > 350) { //third hour and beyond
            timeBought = insertedSoFar / 5;
        }
        return timeBought;
    }
}
