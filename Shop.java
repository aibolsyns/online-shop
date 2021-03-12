import Clients.*;
import Orders.*;
import Products.*;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class Shop
{
    private final HashMap<String, ArrayList<Product>> products;
    private final HashMap<String, Customer> customers;
    private final ArrayList<Order> orders;
    private final ArrayList<String> categories;
    private Connection dbConnection = null;
    private final Scanner in = new Scanner(System.in);

    public Shop()
    {
        products = new HashMap<>();
        customers = new HashMap<>();
        orders = new ArrayList<>();
        categories = new ArrayList<>();
        categories.add("CPU");
        categories.add("GPU");
        categories.add("HDD");
        categories.add("SSD");
        categories.add("RAM");
        categories.add("Smartphone");
        for(String category : categories)
        {
            products.put(category, new ArrayList<>());
        }
    }

    public Shop(int port, String db, String username, String password)
    {
        this();//Call default constructor for initialize arrays and hash map
        String url = "jdbc:postgresql://localhost:" + port + "/" + db;//create url for connect database
        try
        {
            dbConnection = DriverManager.getConnection(url, username, password);//connect data base
            dbConnection.setAutoCommit(true);
            String query = "SELECT * FROM client";//query for get clients
            Statement statement = null;
            try
            {
                statement = dbConnection.createStatement();
                ResultSet res = statement.executeQuery(query);
                while(res.next())//Check to has data
                {
                    String email = res.getString("email");
                    String firstName = res.getString("name");
                    String lastName = res.getString("surname");
                    double discount = res.getFloat("discount");
                    double cache = res.getFloat("cache");
                    //read data
                    if(cache > 0) // check to primary client, if client has cache that client is primary
                    {
                        customers.put(email, new PrimaryCustomer(email, firstName, lastName, discount, cache));
                    }
                    else if(discount > 0) // if client has a discount that mean client is regular
                    {
                        customers.put(email, new RegularCustomer(email, firstName, lastName, discount));
                    }
                    else
                    {
                        customers.put(email, new OrdinaryCustomer(email, firstName, lastName));
                    }
                }
                res.close();
            }
            catch (SQLException e)
            {
                if(statement != null)//if we have statement, we close it
                {
                    statement.close();
                }
            }
            finally
            {
                if(statement != null)
                {
                    statement.close();
                }
            }
        }
        catch (SQLException e)
        {
            if(dbConnection != null)//if we connect db we close it
            {
                try
                {
                    dbConnection.close();
                }
                catch (SQLException ex)
                {
                    System.out.println(ex.getMessage());//if we can close db we output the exception
                }
            }
        }
    }

    public void addClient()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter email");
        String email = in.next();
        System.out.println("Enter name");
        String name = in.next();
        System.out.println("Enter surname");
        String surname = in.next();
        if(!customers.containsKey(email))//If this client not existed, we add client to hash map
        {
            customers.put(email, new OrdinaryCustomer(email, name, surname));
            if(dbConnection != null)//Check the connection, if we connect to database, add new client to database
            {
                Statement cmd = null;
                try
                {
                    cmd = dbConnection.createStatement();
                    var res = cmd.executeUpdate("INSERT INTO client(email, name, surname, discount, cache) VALUES('" + email + "', " +
                            "'" + name + "', '" + surname + "', 0, 0);");

                    cmd.close();
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void addProduct()
    {
        System.out.println("Enter category");
        String category = in.next().toLowerCase();
        for(String item : categories)
        {
            if(category.equals(item.toLowerCase()))//Check to contains the category
            {
                String name = "Products." + item;
                try
                {
                    Parameter[] params = Class.forName(name).getConstructors()[0].getParameters();//Try to create get parameters of first constructor
                    String[] fieldNames = new String[params.length];
                    Field[] fieldsOfProduct = Class.forName("Products.Product").getDeclaredFields();//Get fields from Product because is basic class of all products
                    int index = 0;
                    for (int i = 0; i < fieldsOfProduct.length; ++i)
                    {
                        fieldNames[index++] = fieldsOfProduct[i].getName();
                    }
                    Field[] fieldsOfClass = Class.forName(name).getDeclaredFields();//Get fields from derived class
                    for (int i = 0; i < fieldsOfClass.length && index < params.length; ++i)
                    {
                        fieldNames[index++] = fieldsOfClass[i].getName();
                    }
                    Object[] arguments = new Object[params.length];
                    for(int i = 0; i < params.length; ++i)
                    {
                        System.out.println("Input the " + fieldNames[i]);//Output which argument is input
                        //Check type to read from input
                        if(params[i].getType().getSimpleName().equals("String"))
                        {
                            arguments[i] = in.next();
                        }
                        else if(params[i].getType().getSimpleName().equals("int"))
                        {
                            arguments[i] = in.nextInt();
                        }
                        else if(params[i].getType().getSimpleName().equals("double"))
                        {
                            arguments[i] = in.nextDouble();
                        }
                    }
                    Object newProduct =  Class.forName(name).getConstructors()[0].newInstance(arguments);//Call first constructor of object and take the arguments
                    products.get(item).add((Product)newProduct);
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Incorrect category");
    }

    public void makeOrder(String email)
    {
        if(!customers.containsKey(email))
        {
            System.out.println("Email address incorrect");
        }
        else
        {
            Order order = new Order();
            Customer customer = customers.get(email);
            do
            {
                System.out.println("Enter category");
                String category = in.next().toLowerCase();
                for (String item : categories)
                {
                    if (category.equals(item.toLowerCase()))
                    {
                        category = item;
                        break;
                    }
                }
                if (products.containsKey(category))
                {
                    for (int i = 0; i < products.get(category).size(); ++i)
                    {
                        System.out.println(i + ") " + products.get(category).get(i));
                    }
                    System.out.println("Enter the index");
                    int index = in.nextInt();
                    System.out.println("Enter count of product");
                    int count = in.nextInt();
                    try
                    {
                        Product product = products.get(category).get(index);
                        OrderItem item = new OrderItem(product, count);
                        order.getItems().add(item);
                        System.out.println("If you want to buy one more product enter 1 else 2");
                        int variant = in.nextInt();
                        while (variant != 1 && variant != 2)
                        {
                            System.out.println("Please enter correct answer");
                            variant = in.nextInt();
                        }
                        if(variant == 2)
                        {
                            break;
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Incorrect index");
                    }
                }
                else
                {
                    System.out.println("Category not found");
                }
            }
            while(true);
            customer.buy(order);
            if(customer.getHistory().size() > 10)//Update customers
            {
                customers.put(customer.getEmail(), new PrimaryCustomer(customer.getEmail(), customer.getName(), customer.getSurname()));
            }
            else if(customer.getHistory().size() > 5)
            {
                customers.put(customer.getEmail(), new RegularCustomer(customer.getEmail(), customer.getName(), customer.getSurname()));
            }
            orders.add(order);
        }
    }

    public void showProducts()
    {
        ArrayList<Product> productsShow = new ArrayList<>();
        for(List<Product> list : products.values())
        {
            productsShow.addAll(list);
        }
        productsShow.sort((a, b)-> (int) (b.getPrice() - a.getPrice()));
        for (Product product : productsShow)
        {
            System.out.println(product.toString());
        }
    }

    public void close()
    {
        if(dbConnection != null)
        {
            try
            {
                dbConnection.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        in.close();
    }
}
