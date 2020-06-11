package comparators;

import entities.Patient;

import java.util.Comparator;

public class SeverityComparator implements Comparator<Patient> {
    @Override
    public final int compare(Patient o1, Patient o2) {
        return -o1.getState().getSeverity() + o2.getState().getSeverity();
    }
}
