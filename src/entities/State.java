package entities;

import source.IllnessType;

public class State {
    private IllnessType illnessName;
    private int severity;

    public final IllnessType getIllnessName() {
        return illnessName;
    }

    public final void setIllnessName(IllnessType illnessName) {
        this.illnessName = illnessName;
    }

    public final int getSeverity() {
        return severity;
    }

    public final void setSeverity(int severity) {
        this.severity = severity;
    }
}
