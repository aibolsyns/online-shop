package Products;

public abstract class Product
{
    protected String name;
    protected double price;
    protected String manufacture;

    public Product(String name, double price, String manufacture) // Constructor
    {
        this.name = name;
        this.price = price;
        this.manufacture = manufacture;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacture() {
        return manufacture;
    }
}
