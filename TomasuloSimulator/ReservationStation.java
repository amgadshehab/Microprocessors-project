// ReservationStation.java
public class ReservationStation {
    String instruction;    // The instruction being executed
    String status;         // Status: "waiting", "executing", "completed"
    String op;             // Operation (e.g., ADD, SUB, MUL)
    int operand1, operand2; // Operands
    int result;            // The result once the operation is complete
    boolean ready;         // If operands are ready
    boolean isFloatingPoint; // New flag for floating-point operations
    int latency;          // Latency for the current instruction
    int cycleStart;       // Cycle when instruction started

    public ReservationStation(String op, int latency) {
        this.op = op;
        this.status = "waiting";
        this.ready = false;
        this.latency = latency;
    }


    // Set operands and mark as ready if both operands are available
    public void setOperands(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.ready = true;
    }

    public boolean isReady() {
        return ready;
    }

    public void execute(int cycle) {
        if (status.equals("waiting") && ready) {
            status = "executing";
            cycleStart = cycle;
        }
        // Execute after latency
        if (status.equals("executing") && (cycle - cycleStart >= latency)) {
            completeExecution();
        }
    }

    private void completeExecution() {
        if (op.equals("ADD")) {
            result = operand1 + operand2;
        } else if (op.equals("SUB")) {
            result = operand1 - operand2;
        } else if (op.equals("L.S")) {
            result = operand1;  // Simplified for now
        }
        status = "completed";
    }

    public void complete() {
        if (status.equals("executing")) {
            status = "completed";
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s %d, %d -> %d", op, status, operand1, operand2, result);
    }
}
