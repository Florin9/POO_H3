package observers;

import comparators.NameComparator;
import entities.Doctor;
import entities.ERSimulator;
import entities.Patient;
import entities.SimulationManager;
import source.PatientState;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DoctorCheckObserver implements Observer {
    @Override
    public final void update(Observable o, Object arg) {
        System.out.println("~~~~ Doctors check their hospitalized"
                + " patients and give verdicts ~~~~");
        ERSimulator simulator = ((RoundCounter) o).getSimulator();
        for (Doctor d : simulator.getStartDoctors()) {
            ArrayList<Patient> toRemove = new ArrayList<>();
            d.getInternees().sort(new NameComparator());
            for (Patient p : d.getInternees()) {
                if (p.getState().getSeverity() <= 0 || p.getTreatment().getNumberOfRounds() <= 0) {
                    p.setPatientState(PatientState.HOME_DONE_TREATMENT);
                    System.out.println(d.getType().getValue() + " sent " + p.getName() + " home");
                    SimulationManager.getInstance().getInternatedPatients().remove(p);
                    toRemove.add(p);
                } else {
                    System.out.println(d.getType().getValue() + " says that " + p.getName()
                            + " should remain in hospital");
                }
            }
            d.getInternees().removeAll(toRemove);
        }
        System.out.println();
    }
}
