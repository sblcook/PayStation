package paystation.domain;

public interface RateStrategy {

    String linear = "Linear";
    String progressive = "Progressive";
    String alternating = "Alternating";

    //changes rate strategy between linear, progressive, or mixed
    void changeRateStrategy(String newRateStrategy);

    double calculateTime(int insertedSoFar);
}
