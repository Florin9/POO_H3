package entities;

import source.DoctorType;
import source.IllnessType;
import source.Numbers;
import source.PatientAction;
import source.PatientState;

import java.util.ArrayList;

public class Doctor {
    private static final int AMELIORATIVE = 22;
    private static final int MIN_ROUNDS_FOR_TREATMENT = 3;
    private boolean isSurgeon;
    private ArrayList<Patient> internees = new ArrayList<>();
    private double c1;
    private double c2;
    private int t = AMELIORATIVE;
    private int maxForTreatment;
    private DoctorType type;
    private ArrayList<IllnessType> treatedIllness;

    public final boolean getisSurgeon() {
        return isSurgeon;
    }

    public final void setisSurgeon(boolean surgeon) {
        isSurgeon = surgeon;
    }

    public final int getMaxForTreatment() {
        return maxForTreatment;
    }

    public final void setMaxForTreatment(int maxForTreatment) {
        this.maxForTreatment = maxForTreatment;
    }

    public final DoctorType getType() {
        return type;
    }

    public final void setType(DoctorType type) {
        this.type = type;
        updatestats();
    }

    private void updatestats() {
        treatedIllness = new ArrayList<>();
        switch (type) {
            case CARDIOLOGIST:
                c1 = Numbers.C1_CARDIOLOGIST;
                c2 = Numbers.C2_CARDIOLOGIST;
                treatedIllness.add(IllnessType.HEART_ATTACK);
                treatedIllness.add(IllnessType.HEART_DISEASE);
                break;
            case ER_PHYSICIAN:
                c1 = Numbers.C2_CARDIOLOGIST;
                c2 = Numbers.C2_PHYSICIAN;
                treatedIllness.add(IllnessType.ALLERGIC_REACTION);
                treatedIllness.add(IllnessType.BROKEN_BONES);
                treatedIllness.add(IllnessType.BURNS);
                treatedIllness.add(IllnessType.CAR_ACCIDENT);
                treatedIllness.add(IllnessType.CUTS);
                treatedIllness.add(IllnessType.HIGH_FEVER);
                treatedIllness.add(IllnessType.SPORT_INJURIES);
                break;
            case GASTROENTEROLOGIST:
                c1 = Numbers.C1_GASTRO;
                treatedIllness.add(IllnessType.ABDOMINAL_PAIN);
                treatedIllness.add(IllnessType.ALLERGIC_REACTION);
                treatedIllness.add(IllnessType.FOOD_POISONING);
                break;
            case GENERAL_SURGEON:
                c1 = Numbers.C_SURGEON;
                c2 = Numbers.C_SURGEON;
                treatedIllness.add(IllnessType.ABDOMINAL_PAIN);
                treatedIllness.add(IllnessType.BURNS);
                treatedIllness.add(IllnessType.CAR_ACCIDENT);
                treatedIllness.add(IllnessType.CUTS);
                treatedIllness.add(IllnessType.SPORT_INJURIES);
                break;
            case INTERNIST:
                c1 = Numbers.C1_INTERNIST;
                treatedIllness.add(IllnessType.ABDOMINAL_PAIN);
                treatedIllness.add(IllnessType.ALLERGIC_REACTION);
                treatedIllness.add(IllnessType.FOOD_POISONING);
                treatedIllness.add(IllnessType.HEART_DISEASE);
                treatedIllness.add(IllnessType.HIGH_FEVER);
                treatedIllness.add(IllnessType.PNEUMONIA);
                break;
            case NEUROLOGIST:
                c1 = Numbers.C1_GASTRO;
                c2 = Numbers.C2_CARDIOLOGIST;
                treatedIllness.add(IllnessType.STROKE);
                break;
            default:
                break;
        }
    }

    public final ArrayList<Patient> getInternees() {
        return internees;
    }

    public final void setInternees(ArrayList<Patient> internees) {
        this.internees = internees;
    }

    public final double getC1() {
        return c1;
    }

    public final void setC1(float c1) {
        this.c1 = c1;
    }

    public final double getC2() {
        return c2;
    }

    public final void setC2(float c2) {
        this.c2 = c2;
    }

    public final int getT() {
        return t;
    }

    public final void setT(int t) {
        this.t = t;
    }

    public final ArrayList<IllnessType> getTreatedIllness() {
        return treatedIllness;
    }

    public final void setTreatedIllness(ArrayList<IllnessType> treatedIllness) {
        this.treatedIllness = treatedIllness;
    }

    public final void consult(Patient a) {
        if (a.getState().getSeverity() <= maxForTreatment) {
            switch (type) {
                case NEUROLOGIST:
                    a.setPatientState(PatientState.HOME_NEURO);
                    break;
                case INTERNIST:
                    a.setPatientState(PatientState.HOME_INTERNIST);
                    break;
                case GENERAL_SURGEON:
                    a.setPatientState(PatientState.HOME_SURGEON);
                    break;
                case GASTROENTEROLOGIST:
                    a.setPatientState(PatientState.HOME_GASTRO);
                    break;
                case ER_PHYSICIAN:
                    a.setPatientState(PatientState.HOME_ERPHYSICIAN);
                    break;
                case CARDIOLOGIST:
                    a.setPatientState(PatientState.HOME_CARDIO);
                    break;
                default:
                    break;
            }
            a.setAction(PatientAction.HOME);
        } else {
            SimulationManager.getInstance().getInvestigationQueue().add(a);
            a.setPatientState(PatientState.INVESTIGATIONSQUEUE);
        }
        a.setAssignedDoctor(null);
    }

    public void checkHospitalized() {

    }

    public final void decide(Patient a) {
        switch (a.getAction()) {
            case TREATMENT:
                switch (type) {
                    case NEUROLOGIST:
                        a.setPatientState(PatientState.HOME_NEURO);
                        break;
                    case INTERNIST:
                        a.setPatientState(PatientState.HOME_INTERNIST);
                        break;
                    case GENERAL_SURGEON:
                        a.setPatientState(PatientState.HOME_SURGEON);
                        break;
                    case GASTROENTEROLOGIST:
                        a.setPatientState(PatientState.HOME_GASTRO);
                        break;
                    case ER_PHYSICIAN:
                        a.setPatientState(PatientState.HOME_ERPHYSICIAN);
                        break;
                    case CARDIOLOGIST:
                        a.setPatientState(PatientState.HOME_CARDIO);
                        break;
                    default:
                }
                a.setAction(PatientAction.HOME);
                break;
            case HOSPITALIZE:
                switch (type) {
                    case NEUROLOGIST:
                        a.setPatientState(PatientState.HOSPITALIZED_NEURO);
                        break;
                    case INTERNIST:
                        a.setPatientState(PatientState.HOSPITALIZED_INTERNIST);
                        break;
                    case GENERAL_SURGEON:
                        a.setPatientState(PatientState.HOSPITALIZED_SURGEON);
                        break;
                    case GASTROENTEROLOGIST:
                        a.setPatientState(PatientState.HOSPITALIZED_GASTRO);
                        break;
                    case ER_PHYSICIAN:
                        a.setPatientState(PatientState.HOSPITALIZED_ERPHYSICIAN);
                        break;
                    case CARDIOLOGIST:
                        a.setPatientState(PatientState.HOSPITALIZED_CARDIO);
                        int c = 2;
                        break;
                    default:
                        break;
                }
                internees.add(a);
                SimulationManager.getInstance().getInternatedPatients().add(a);
                int numberOfRounds = MIN_ROUNDS_FOR_TREATMENT;
                if (a.getState().getSeverity() * c1 > MIN_ROUNDS_FOR_TREATMENT) {
                    numberOfRounds = (int) Math.round(a.getState().getSeverity() * c1);
                }
                a.setTreatment(new Treatment(numberOfRounds, t));
                break;
            case OPERATE:
                int severity = a.getState().getSeverity();
                int newSeverity = severity - (int) Math.round(severity * c2);
                a.getState().setSeverity(newSeverity);
                switch (type) {
                    case NEUROLOGIST:
                        a.setPatientState(PatientState.OPERATED_NEURO);
                        break;
                    case GENERAL_SURGEON:
                        a.setPatientState(PatientState.OPERATED_SURGEON);
                        break;
                    case ER_PHYSICIAN:
                        a.setPatientState(PatientState.OPERATED_ERPHYSICIAN);
                        break;
                    case CARDIOLOGIST:
                        a.setPatientState(PatientState.OPERATED_CARDIO);
                        break;
                    default:
                        break;
                }
                internees.add(a);
                SimulationManager.getInstance().getInternatedPatients().add(a);
                numberOfRounds = MIN_ROUNDS_FOR_TREATMENT;
                if (a.getState().getSeverity() * c1 > MIN_ROUNDS_FOR_TREATMENT) {
                    numberOfRounds = (int) Math.round(a.getState().getSeverity() * c1);
                }
                a.setTreatment(new Treatment(numberOfRounds, t));
                break;
            default:
        }
    }
}
