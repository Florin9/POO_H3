package observers;

import comparators.NameComparator;
import entities.Patient;
import entities.SimulationManager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PatientPrintObserver implements Observer {
    @Override
    public final void update(Observable o, Object arg) {
        System.out.println("~~~~ Patients in round " + (((RoundCounter) o).getRoundNumber() + 1)
                + " ~~~~");
        ArrayList<Patient> patients = SimulationManager.getInstance().getExaminedPatients();
        patients.sort(new NameComparator());
        for (Patient a : patients) {
            System.out.println(a.getName() + " is " + a.getPatientState().getValue());
        }
        System.out.println();
    }
}
