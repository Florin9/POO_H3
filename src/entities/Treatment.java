package entities;

public class Treatment {
    private int numberOfRounds;
    private int t;

    public Treatment(int numberOfRounds, int t) {
        this.numberOfRounds = numberOfRounds;
        this.t = t;
    }

    public final int getNumberOfRounds() {
        return numberOfRounds;
    }

    public final void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public final int getT() {
        return t;
    }

    public final void setT(int t) {
        this.t = t;
    }
}
