package comparators;

import entities.Patient;
import source.Urgency;

import java.util.Comparator;

public class UrgencyComparator implements Comparator<Patient> {
    @Override
    public final int compare(Patient o1, Patient o2) {
        for (Urgency a: Urgency.values()) {
            if (o1.getUrgency() == a && o2.getUrgency() == a) {
                if (o1.getState().getSeverity() == o2.getState().getSeverity()) {
                    return -o1.getName().compareTo(o2.getName());
                }
                if (o1.getState().getSeverity() > o2.getState().getSeverity()) {
                    return -1;
                }
                return 1;
            }
            if (o1.getUrgency() == a) {
                return -1;
            }
            if (o2.getUrgency() == a) {
                return +1;
            }
        }
        return 0;
    }
}
