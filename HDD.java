package Products;

public class HDD extends Memory
{
    public HDD(String name, double price, String manufacture, int capacity, int speed) {
        super(name, price, manufacture, capacity, speed);
    }

    @Override
    public String toString() {
        return "HDD{" +
                "capacity=" + capacity +
                ", speed=" + speed +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufacture='" + manufacture + '\'' +
                '}';
    }
}
