package observers;

import entities.ERSimulator;

import java.util.Observable;

public class RoundCounter extends Observable {
    private int roundNumber = -1;
    private ERSimulator simulator;

    public final ERSimulator getSimulator() {
        return simulator;
    }

    public final void setSimulator(ERSimulator simulator) {
        this.simulator = simulator;
    }

    public final int getRoundNumber() {
        return roundNumber;
    }

    public final void increaseRoundNumber() {
        roundNumber++;
        this.setChanged();
        this.notifyObservers();
    }
}
