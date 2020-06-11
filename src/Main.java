import com.fasterxml.jackson.databind.ObjectMapper;
import entities.ERSimulator;
import entities.SimulationManager;

import java.io.File;

public final class Main {
    private Main() {
    }

    public static void main(String[]args) {
        ObjectMapper objectMapper = new ObjectMapper();
        ERSimulator simulator = ERSimulator.getInstance();
        try {
            simulator = objectMapper.readValue(new File(args[0]), ERSimulator.class);
        } catch (Throwable a) {
            a.printStackTrace();
        }
        simulator.backupDoctorStartList();
        SimulationManager simulationManager = SimulationManager.getInstance();
        simulationManager.setSimulator(simulator);
        simulationManager.startSimulation();
        int debug = 2;
    }
}
