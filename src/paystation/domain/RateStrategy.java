package paystation.domain;

public interface RateStrategy {

    //changes rate strategy between linear, progressive, or mixed
    void changeRateStrategy();

    int calculateTime(int insertedSoFar);
}
