import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Shop shop = new Shop(5432, "javaend", "postgres", "postgres");
        int variant = -1;
        while(variant != 0)
        {
            System.out.println("1. Add customer\n2. Add product\n3. Make order\n4. Show products\n0. Exit");
            variant = in.nextInt();
            switch (variant)
            {
                case 1:
                    shop.addClient();
                    break;
                case 2:
                    shop.addProduct();
                    break;
                case 3:
                    System.out.println("Enter email");
                    String email = in.next();
                    shop.makeOrder(email);
                    break;
                case 4:
                    shop.showProducts();
                    break;
            }
        }
        shop.close();
    }
}
