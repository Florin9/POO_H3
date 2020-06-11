package entities;

import java.util.ArrayList;

public final class ERSimulator {
    private int simulationLength;
    private int nurses;
    private int investigators;
    private ArrayList<Doctor> doctors;
    private ArrayList<Doctor> startDoctors;
    private ArrayList<Patient> incomingPatients;
    private static ERSimulator instance = null;
    private ERSimulator() { }

    public static ERSimulator getInstance() {
        if (instance == null)  {
            instance = new ERSimulator();
        }
        return instance;
    }

    public int getSimulationLength() {
        return simulationLength;
    }

    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Patient> getIncomingPatients() {
        return incomingPatients;
    }

    public void setIncomingPatients(ArrayList<Patient> incomingPatients) {
        this.incomingPatients = incomingPatients;
    }

    public int getNurses() {
        return nurses;
    }

    public void setNurses(int nurses) {
        this.nurses = nurses;
    }

    public int getInvestigators() {
        return investigators;
    }

    public void setInvestigators(int investigators) {
        this.investigators = investigators;
    }

    public void removeDoctor(Doctor doctorOnJob) {
        if (doctorOnJob == null) {
            return;
        }
        doctors.remove(doctorOnJob);
        doctors.add(doctorOnJob);
    }
    public void backupDoctorStartList() {
        startDoctors = new ArrayList<>();
        startDoctors.addAll(doctors);
    }

    public ArrayList<Doctor> getStartDoctors() {
        return startDoctors;
    }
}
