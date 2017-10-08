package paystation.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlternatingRateStrategy implements RateStrategy{

    Calendar calendar = new GregorianCalendar();

    @Override
    public double calculateTime(int insertedSoFar){
        double timeBought = 0;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == 1 || dayOfWeek == 7){
            LinearRateStrategy linearRateStrategy = new LinearRateStrategy();
            timeBought = linearRateStrategy.calculateTime(insertedSoFar);
        } else if (dayOfWeek > 1 && dayOfWeek < 7){
            ProgressiveRateStrategy progressiveRateStrategy = new ProgressiveRateStrategy();
            timeBought = progressiveRateStrategy.calculateTime(insertedSoFar);
        }
        return timeBought;
    }
}
