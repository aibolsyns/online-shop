package Products;

public abstract class Memory extends Product
{
    protected int capacity;
    protected int speed;

    public Memory(String name, double price, String manufacture, int capacity, int speed) {
        super(name, price, manufacture);
        this.capacity = capacity;
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }
}
