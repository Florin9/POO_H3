package entities;

import source.PatientAction;
import source.PatientState;
import source.Urgency;

import java.util.ArrayList;

public class Patient {
    private int severity;
    private Urgency urgency = Urgency.NOT_DETERMINED;
    private Treatment treatment;
    private int roundsForTreatment;
    private int id;
    private String name;
    private int age;
    private int time;
    private State state;
    private boolean seen = false;
    private ArrayList<Doctor> doctors;
    private Doctor assignedDoctor;
    private PatientState patientState;
    private PatientAction action = PatientAction.NOT_DIAGNOSED;

    public final void removeDoctor(Doctor doctor) {
        if (doctors.contains(doctor)) {
            doctors.remove(doctor);
            doctors.add(doctor);
        }
    }
    public final Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public final void setAssignedDoctor(Doctor assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getAge() {
        return age;
    }

    public final void setAge(int age) {
        this.age = age;
    }

    public final int getTime() {
        return time;
    }

    public final void setTime(int time) {
        this.time = time;
    }

    public final State getState() {
        return state;
    }

    public final void setState(State state) {
        this.state = state;
    }

    public final int getSeverity() {
        return severity;
    }

    public final void setSeverity(int severity) {
        this.severity = severity;
    }

    public final Urgency getUrgency() {
        return urgency;
    }

    public final void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public final Treatment getTreatment() {
        return treatment;
    }

    public final void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public final int getRoundsForTreatment() {
        return roundsForTreatment;
    }

    public final void setRoundsForTreatment(int roundsForTreatment) {
        this.roundsForTreatment = roundsForTreatment;
    }

    public final boolean isSeen() {
        return seen;
    }

    public final void setSeen(boolean seen) {
        this.seen = seen;
    }

    public final ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public final void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public final PatientState getPatientState() {
        return patientState;
    }

    public final void setPatientState(PatientState patientState) {
        this.patientState = patientState;
    }

    public final PatientAction getAction() {
        return action;
    }

    public final void setAction(PatientAction action) {
        this.action = action;
    }
}
