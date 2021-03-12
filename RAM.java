package Products;

public class RAM extends Product
{
    private final int capacity;
    private final int frequency;

    public RAM(String name, double price, String manufacture, int capacity, int frequency)
    {
        super(name, price, manufacture);
        this.capacity = capacity;
        this.frequency = frequency;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", manufacture='" + manufacture + '\'' +
                ", capacity=" + capacity +
                ", frequency=" + frequency +
                '}';
    }
}
