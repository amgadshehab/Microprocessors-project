// RegisterFile.java
public class RegisterFile {
    private int[] registers = new int[32];  // MIPS has 32 registers

    public RegisterFile() {
        // Optionally initialize registers with values, or load values from the user input
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;  // Initial value of registers
        }
    }

    public int getValue(int register) {
        return registers[register];
    }

    public void setValue(int register, int value) {
        registers[register] = value;
    }

    public void printRegisters() {
        for (int i = 0; i < registers.length; i++) {
            System.out.printf("R%d = %d\n", i, registers[i]);
        }
    }
}
