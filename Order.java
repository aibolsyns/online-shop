package Orders;

import java.util.ArrayList;
import java.util.Date;

public class Order
{
    private final ArrayList<OrderItem> items;
    private double totalPrice;
    private final Date date;

    public Order()
    {
        this.items = new ArrayList<>();
        totalPrice = 0;
        date = new Date();
    }

    public Order(ArrayList<OrderItem> items)
    {
        this.items = items;
        totalPrice = 0;
        date = new Date();
        for(var item : items)
        {
            totalPrice += item.getTotalPrice();
        }
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice()//In method recalculate total price of order
    {
        totalPrice = 0;
        for(var item : items)
        {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
