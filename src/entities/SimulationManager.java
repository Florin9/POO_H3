package entities;

import observers.DoctorCheckObserver;
import observers.NurseCheckObserver;
import observers.PatientPrintObserver;
import observers.RoundCounter;

import java.util.ArrayList;

public final class SimulationManager {
    private static SimulationManager instance = null;
    private ArrayList<Patient> triageQueue;
    private ArrayList<Patient> examinationQueue;
    private ArrayList<Patient> investigationQueue;
    private ArrayList<Patient> internatedPatients = new ArrayList<>();

    private SimulationManager() {
        triageQueue = new ArrayList<>();
        examinationQueue = new ArrayList<>();
        investigationQueue = new ArrayList<>();
    }
    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }
    public ArrayList<Patient> getInternatedPatients() {
        return internatedPatients;
    }

    private ERSimulator simulator;
    private ArrayList<Patient> examinedPatients = new ArrayList<>();

    public void startSimulation() {
        RoundCounter roundCounter = new RoundCounter();
        roundCounter.setSimulator(simulator);
        roundCounter.addObserver(new DoctorCheckObserver());
        roundCounter.addObserver(new NurseCheckObserver());
        roundCounter.addObserver(new PatientPrintObserver());
        for (int i = 0; i < simulator.getSimulationLength(); i++) {
            RoundManager round = new RoundManager(simulator, i, this);
            round.start();
            roundCounter.increaseRoundNumber();
        }
    }

    public ArrayList<Patient> getTriageQueue() {
        return triageQueue;
    }

    public void setTriageQueue(ArrayList<Patient> triageQueue) {
        this.triageQueue = triageQueue;
    }

    public ArrayList<Patient> getExaminationQueue() {
        return examinationQueue;
    }

    public void setExaminationQueue(ArrayList<Patient> examinationQueue) {
        this.examinationQueue = examinationQueue;
    }

    public ArrayList<Patient> getInvestigationQueue() {
        return investigationQueue;
    }

    public void setInvestigationQueue(ArrayList<Patient> investigationQueue) {
        this.investigationQueue = investigationQueue;
    }

    public ArrayList<Patient> getExaminedPatients() {
        return examinedPatients;
    }

    public void setExaminedPatients(ArrayList<Patient> examinedPatients) {
        this.examinedPatients = examinedPatients;
    }

    public ERSimulator getSimulator() {
        return simulator;
    }

    public void setSimulator(ERSimulator simulator) {
        this.simulator = simulator;
    }
}
