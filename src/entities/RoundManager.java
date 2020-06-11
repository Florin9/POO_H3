package entities;


import comparators.SeverityComparator;
import comparators.UrgencyComparator;
import source.Numbers;
import source.PatientAction;
import source.PatientState;
import source.UrgencyEstimator;

import java.util.ArrayList;

public class RoundManager {
    private ERSimulator simulator;
    private int roundNumber;
    private SimulationManager simulationManager;

    public RoundManager(ERSimulator simulator, int roundNumber, SimulationManager sM) {
        this.simulator = simulator;
        this.roundNumber = roundNumber;
        this.simulationManager = sM;
    }

    public final void start() {
        ArrayList<Patient> triageQueue = simulationManager.getTriageQueue();
        ArrayList<Patient> examinationQueue = simulationManager.getExaminationQueue();
        for (Patient patient : simulator.getIncomingPatients()) {
            if (patient.getTime() > roundNumber) {
                break;
            }
            if (!patient.isSeen() && patient.getTime() == roundNumber) {
                triageQueue.add(patient);
                patient.setPatientState(PatientState.TRIAGEQUEUE);
                simulationManager.getExaminedPatients().add(patient);
                //patient.setSeen(true);
            }
        }
        triageQueue.sort(new SeverityComparator());
        UrgencyEstimator urgencyEstimator = UrgencyEstimator.getInstance();
        int size = triageQueue.size();
        for (int i = 0; i < size; i++) {
            if (i < simulator.getNurses()) {
                State state = triageQueue.get(0).getState();
                triageQueue.get(0).setUrgency(urgencyEstimator.estimateUrgency(
                        state.getIllnessName(), state.getSeverity()));
                triageQueue.get(0).setPatientState(PatientState.EXAMINATIONSQUEUE);
                examinationQueue.add(triageQueue.get(0));
                triageQueue.get(0).setSeen(true);
                triageQueue.remove(0);
            } else {
                break;
            }
        }
        //examination
        examinationQueue.sort(new UrgencyComparator());
        for (Patient a : examinationQueue) {
            if (a.getDoctors() == null) {
                a.setDoctors(new ArrayList<>());
            }
            for (Doctor doctor : simulator.getDoctors()) {
                if (doctor.getTreatedIllness().contains(a.getState().getIllnessName())) {
                    a.getDoctors().add(doctor);
                }
            }
        }

        for (Patient a : examinationQueue) {
            Doctor doctorOnJob;
            if (a.getAction() == PatientAction.NOT_DIAGNOSED) {
                a.setAssignedDoctor(a.getDoctors().get(0));
                a.getAssignedDoctor().consult(a);
                doctorOnJob = a.getDoctors().get(0);
            } else if (a.getAction() == PatientAction.OPERATE) {
                int i = 0;
                while (i < a.getDoctors().size() && !a.getDoctors().get(i).getisSurgeon()) {
                    i++;
                }
                int ok = 1;
                if (i == (a.getDoctors().size()) && !a.getDoctors().get(i - 1).getisSurgeon()) {
                    ok = 0;
                }
                if (ok == 1) {
                    doctorOnJob = a.getDoctors().get(i);
                    a.setAssignedDoctor(doctorOnJob);
                    a.getAssignedDoctor().decide(a);
                } else {
                    doctorOnJob = null;
                    a.setPatientState(PatientState.OTHERHOSPITAL);
                }

            } else {
                doctorOnJob = a.getDoctors().get(0);
                a.setAssignedDoctor(doctorOnJob);
                a.getAssignedDoctor().decide(a);
            }
            for (Patient b : examinationQueue) {
                b.removeDoctor(doctorOnJob);
            }
            //remove doctor from doctor list, add to end
            simulator.removeDoctor(doctorOnJob);
        }
        //investigation
        examinationQueue.removeAll(examinationQueue);
        ArrayList<Patient> investigationQueue = simulationManager.getInvestigationQueue();
        investigationQueue.sort(new UrgencyComparator());
        size = investigationQueue.size();
        for (int i = 0; i < size; i++) {
            if (i < simulator.getInvestigators()) {
                Patient investigatedPatient = investigationQueue.get(0);
                int severity = investigatedPatient.getState().getSeverity();
                if (severity > Numbers.MAX_FOR_HOSPITALIZE) {
                    investigatedPatient.setAction(PatientAction.OPERATE);
                } else if (severity > Numbers.FORTY) {
                    investigatedPatient.setAction(PatientAction.HOSPITALIZE);
                } else {
                    investigatedPatient.setAction(PatientAction.TREATMENT);
                }
                examinationQueue.add(investigatedPatient);
                investigatedPatient.setPatientState(PatientState.EXAMINATIONSQUEUE);
                investigationQueue.remove(0);
                investigatedPatient.getDoctors().removeAll(investigatedPatient.getDoctors());
            } else {
                break;
            }
        }

    }
}
