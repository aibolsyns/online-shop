package Products;

public class Smartphone extends Product
{
    private final int memory;
    private final int ram;
    private final int camera;
    private final String os;
    private final String processor;

    public Smartphone(String name, double price, String manufacture, int memory, int ram, int camera, String os, String processor) {
        super(name, price, manufacture);
        this.memory = memory;
        this.ram = ram;
        this.camera = camera;
        this.os = os;
        this.processor = processor;
    }

    public int getMemory() {
        return memory;
    }

    public int getRam() {
        return ram;
    }

    public int getCamera() {
        return camera;
    }

    public String getOs() {
        return os;
    }

    public String getProcessor() {
        return processor;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", manufacture='" + manufacture + '\'' +
                ", memory=" + memory +
                ", ram=" + ram +
                ", camera=" + camera +
                ", os='" + os + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }
}
