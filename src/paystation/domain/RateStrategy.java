package paystation.domain;

public interface RateStrategy {

    //changes rate strategy between linear, progressive, or mixed

    double calculateTime(int insertedSoFar);

}
