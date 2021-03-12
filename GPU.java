package Products;

public class GPU extends Product
{
    private final int memory;
    private final int frequency;
    private final String memoryType;

    public GPU(String name, double price, String manufacture, int memory, int frequency, String memoryType) {
        super(name, price, manufacture);
        this.memory = memory;
        this.frequency = frequency;
        this.memoryType = memoryType;
    }

    public int getMemory() {
        return memory;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getMemoryType() {
        return memoryType;
    }
}
