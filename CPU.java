package Products;

public class CPU extends Product
{
    private final int coreCount;
    private final int L1CacheMemory;
    private final int L2CacheMemory;
    private final int L3CacheMemory;
    private final double frequency;
    private boolean hasThreading;
    private boolean isLock;

    public CPU(String name, double price, String manufacture, int coreCount, int l1CacheMemory, int l2CacheMemory, int l3CacheMemory, double frequency) {
        super(name, price, manufacture);
        this.coreCount = coreCount;
        L1CacheMemory = l1CacheMemory;
        L2CacheMemory = l2CacheMemory;
        L3CacheMemory = l3CacheMemory;
        this.frequency = frequency;
    }

    public CPU(String name, double price, String manufacture, int coreCount, int l1CacheMemory, int l2CacheMemory, int l3CacheMemory, double frequency, boolean hasThreading, boolean isLock)
    {
        super(name, price, manufacture);
        this.coreCount = coreCount;
        L1CacheMemory = l1CacheMemory;
        L2CacheMemory = l2CacheMemory;
        L3CacheMemory = l3CacheMemory;
        this.frequency = frequency;
        this.hasThreading = hasThreading;
        this.isLock = isLock;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public int getL1CacheMemory() {
        return L1CacheMemory;
    }

    public int getL2CacheMemory() {
        return L2CacheMemory;
    }

    public int getL3CacheMemory() {
        return L3CacheMemory;
    }

    public double getFrequency() {
        return frequency;
    }

    public boolean isHasThreading() {
        return hasThreading;
    }

    public boolean isLock() {
        return isLock;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "coreCount=" + coreCount +
                ", L1CacheMemory=" + L1CacheMemory +
                ", L2CacheMemory=" + L2CacheMemory +
                ", L3CacheMemory=" + L3CacheMemory +
                ", frequency=" + frequency +
                ", hasThreading=" + hasThreading +
                ", isLock=" + isLock +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufacture='" + manufacture + '\'' +
                '}';
    }
}
