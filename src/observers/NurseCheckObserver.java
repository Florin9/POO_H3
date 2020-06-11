package observers;

import comparators.NameComparator;
import entities.ERSimulator;
import entities.Patient;
import entities.SimulationManager;

import java.util.Observable;
import java.util.Observer;

public class NurseCheckObserver implements Observer {
    @Override
    public final void update(Observable o, Object arg) {
        ERSimulator simulator = ((RoundCounter) o).getSimulator();
        System.out.println("~~~~ Nurses treat patients ~~~~");
        SimulationManager.getInstance().getInternatedPatients().sort(new NameComparator());
        int i = 0;
        for (Patient a : SimulationManager.getInstance().getInternatedPatients()) {
            a.getState().setSeverity(a.getState().getSeverity() - a.getTreatment().getT());
            a.getTreatment().setNumberOfRounds(a.getTreatment().getNumberOfRounds() - 1);
            if (a.getTreatment().getNumberOfRounds() != 1) {
                System.out.println("Nurse " + i % simulator.getNurses()
                        + " treated " + a.getName() + " and patient has "
                        + a.getTreatment().getNumberOfRounds() + " more rounds");
            } else {
                System.out.println("Nurse " + i % simulator.getNurses()
                        + " treated " + a.getName() + " and patient has "
                        + a.getTreatment().getNumberOfRounds() + " more round");
            }
            i++;
        }
        System.out.println();
    }
}
