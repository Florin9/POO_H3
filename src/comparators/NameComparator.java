package comparators;

import entities.Patient;

import java.util.Comparator;

public class NameComparator implements Comparator<Patient> {
    @Override
    public final int compare(Patient o1, Patient o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
