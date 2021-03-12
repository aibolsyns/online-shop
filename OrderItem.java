package Orders;

import Products.Product;

public class OrderItem
{
    private final Product product;
    private int amount;
    private double totalPrice;

    public OrderItem(Product product, int amount)
    {
        this.product = product;
        this.amount = amount;
        totalPrice = product.getPrice() * amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        totalPrice = amount * product.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
