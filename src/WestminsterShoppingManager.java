import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    // Scanner for user input
    public static Scanner input = new Scanner(System.in);

    // Shopping cart instance
    public static ShoppingCart shoppingCart = new ShoppingCart();

    // Main method to interact with the user
    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        westminsterShoppingManager.loadFromFile();

        int num;
        while (true) {
            System.out.println("Enter Your Option: ");
            System.out.println("1. Add product to Shopping Center");
            System.out.println("2. Delete product from Shopping Center");
            System.out.println("3. Products List of Shopping Center");
            System.out.println("4. Save to file");
            System.out.println("5. Open User Interface");
            System.out.println("0. Exit");

            num = returnInt();
            switch (num) {
                case 0:
                    return;
                case 1:
                    westminsterShoppingManager.addProduct();
                    break;
                case 2:
                    westminsterShoppingManager.deleteProduct();
                    break;
                case 3:
                    westminsterShoppingManager.displayProducts();
                    break;
                case 4:
                    westminsterShoppingManager.saveToFile();
                    break;
                case 5:
                    WestminsterShoppingCenter.main(args);
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    // Method to add a new product to the shopping cart
    public void addProduct() {
        if (shoppingCart.getProductList().size() <= 50) {
            int productType;
            int productItems;
            double price;

            while (true) {
                System.out.println("### Choose the product type ###");
                System.out.println("1 for Electronics: ");
                System.out.println("2 for Clothing: ");

                productType = returnInt();
                if (productType == 1 || productType == 2) {
                    break;
                } else {
                    System.out.println("Enter a valid option: ");
                    input.nextLine();
                }
            }

            System.out.print("Product ID: ");
            String productId = input.next();
            System.out.print("Product Name: ");
            String productName = input.next();
            System.out.print("No. of items: ");
            productItems = returnInt();
            System.out.print("Price: ");
            price = returnDouble();

            switch (productType) {
                case 1:
                    System.out.print("Enter the brand: ");
                    String brand = input.next();
                    System.out.print("Enter the warranty period in months: ");
                    int warrantyPeriod = returnInt();
                    Electronics electronics = new Electronics(productId, productName, productItems, price, "electronics", brand, warrantyPeriod);
                    shoppingCart.addProduct(electronics);
                    break;
                case 2:
                    System.out.println("Enter the Size");
                    String size = input.next();
                    System.out.println("Enter the color");
                    String color = input.next();
                    Clothing clothing = new Clothing(productId, productName, productItems, price, "clothing", size, color);
                    shoppingCart.addProduct(clothing);
                    break;
                default:
                    System.out.println("Product not found!");
                    break;
            }
        } else {
            System.out.println("You have reached the limit of 50 products!");
        }
    }

    // Method to delete a product from the shopping cart
    public void deleteProduct() {
        displayProducts();

        while (true) {
            System.out.println("Choose the product type");
            System.out.println("1 for Electronics");
            System.out.println("2 for Clothing :");

            int productType = returnInt();

            System.out.println("Enter product ID");
            String productId = input.next();

            boolean deleted = false;

            switch (productType) {
                case 1:
                    for (int i = 0; i < shoppingCart.getProductList().size(); i++) {
                        if (productId.equals(shoppingCart.getProductList().get(i).getProductId()) && "electronics".equals(shoppingCart.getProductList().get(i).getObjecttype())) {
                            shoppingCart.getProductList().get(i).productDetails();
                            shoppingCart.deleteProduct(shoppingCart.getProductList().get(i));
                            deleted = true;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < shoppingCart.getProductList().size(); i++) {
                        if (productId.equals(shoppingCart.getProductList().get(i).getProductId()) && "clothing".equals(shoppingCart.getProductList().get(i).getObjecttype())) {
                            shoppingCart.getProductList().get(i).productDetails();
                            shoppingCart.deleteProduct(shoppingCart.getProductList().get(i));
                            deleted = true;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid product type!");
            }

            if (deleted) {
                System.out.println("Product deleted successfully.");
                System.out.println("Products remaining in the Cart: " + shoppingCart.getProductList().size());
                break;
            } else {
                System.out.println("Product not found.");
            }
        }
    }

    // Method to display products in the shopping cart
    public void displayProducts() {
        if (shoppingCart.getProductList().isEmpty()) {
            System.out.println("No products in the Cart.");
        } else {
            for (int i = 0; i < shoppingCart.getProductList().size(); i++) {
                System.out.println(shoppingCart.getProductList().get(i).productDetails());
            }
        }
    }

    // Method to save products to a file
    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter("savedProducts.txt");
            for (int i = 0; i < shoppingCart.getProductList().size(); i++) {
                myWriter.write(shoppingCart.getProductList().get(i).productDetails() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("No saved products found.");
        }
    }

    // Method to load products from a file
    public void loadFromFile() {
        try {
            File savedProducts = new File("savedProducts.txt");
            Scanner fileReader = new Scanner(savedProducts);
            while (fileReader.hasNextLine()) {
                String dataLine = fileReader.nextLine();
                String[] data = dataLine.split("\\|");

                if (data.length == 7) {
                    if (data[6].equals("electronics")) {
                        Electronics electronics = new Electronics(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[6], data[4], Integer.parseInt(data[5]));
                        shoppingCart.addProduct(electronics);
                    } else if (data[6].equals("clothing")) {
                        Clothing clothing = new Clothing(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[6], data[4], data[5]);
                        shoppingCart.addProduct(clothing);
                    }
                } else {
                    System.out.println("Invalid data format in the file.");
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    // Utility method to get an integer from user input
    private static int returnInt() {
        int value;
        while (true) {
            try {
                value = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("Enter a valid value: ");
                input.nextLine();
            }
        }
        return value;
    }

    // Utility method to get a double from user input
    private static double returnDouble() {
        double value;
        while (true) {
            try {
                value = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print("Enter a valid price: ");
                input.nextLine();
            }
        }
        return value;
    }
}