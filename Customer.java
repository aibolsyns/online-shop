package Clients;

import Orders.Order;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Customer
{
    protected String email;
    protected String name;
    protected String surname;
    protected ArrayList<Order> history;

    public Customer(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        history = new ArrayList<>();
    }

    public abstract void buy(Order order);//Method for buy a products

    protected double enterMoney(double totalPrice)//Method to get money from client
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter money");
        double price = in.nextDouble();
        while(price < totalPrice)//while not enough money we add
        {
            System.out.println("Not enough money, please add");
            double add = in.nextDouble();
            price += add;
        }
        return price;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Order> getHistory() {
        return history;
    }
}
