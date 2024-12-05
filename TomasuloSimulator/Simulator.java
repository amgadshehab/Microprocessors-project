// Simulator.java
import java.util.*;

public class Simulator {
    private RegisterFile registerFile;
    private List<ReservationStation> reservationStations;
    private int cycle;
    private Cache dataCache;

    public Simulator(int cacheSize, int blockSize, int latency) {
        this.registerFile = new RegisterFile();
        this.reservationStations = new ArrayList<>();
        this.cycle = 0;
        this.dataCache = new Cache(cacheSize, blockSize, latency);
    }

    // Add reservation stations (e.g., for ALU operations)
    public void addReservationStation(ReservationStation rs) {
        reservationStations.add(rs);
    }

    // Start the simulation
    public void startSimulation() {
        while (hasInstructionsToExecute()) {
            cycle++;
            System.out.println("Cycle " + cycle);
            executeCycle();
            printState();
        }
    }

    // Execute each cycle: Issue, Execute, Write-back
    private void executeCycle() {
        issueInstructions();
        for (ReservationStation rs : reservationStations) {
            rs.execute(cycle);  // Pass the current cycle to execute() for latency management
        }
        handleWriteBack();
    }

    private void handleWriteBack() {
        for (ReservationStation rs : reservationStations) {
            if (rs.status.equals("completed")) {
                // Update register file
                registerFile.setValue(0, rs.result);  // Example: Write result to register 0
                rs.status = "written";  // Mark as written back
            }
        }
    }

    private boolean hasInstructionsToExecute() {
        // Check if any instructions are still in progress
        return reservationStations.stream().anyMatch(rs -> !rs.status.equals("written"));
    }


    private void issueInstructions() {
        // Issue instructions to reservation stations if they are available and ready
        for (ReservationStation rs : reservationStations) {
            if (rs.status.equals("waiting") && rs.isReady()) {
                rs.status = "issued";
            }
        }
    }

    private void printState() {
        System.out.println("State after cycle " + cycle);
        registerFile.printRegisters();
        for (ReservationStation rs : reservationStations) {
            System.out.println(rs);
        }
    }
    
    // Simulate cache access
    public void simulateMemoryAccess(int address) {
        int result = dataCache.accessMemory(address);
        if (result == -1) {
            System.out.println("Cache miss! Fetching from memory...");
            dataCache.handleCacheMiss(address);
        } else {
            System.out.println("Cache hit! Address " + address + " is in cache.");
        }
    }

}
