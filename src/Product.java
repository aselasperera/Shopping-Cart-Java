public abstract class Product implements Comparable<Product>{
    private String objecttype;
    private String ProductId;
    private String ProductName;
    private int numberofItems;
    private double price;

    public Product(String productId, String productName, int numberofItems, double price, String objecttype) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.numberofItems = numberofItems;
        this.price = price;
        this.objecttype =objecttype;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getNumberofItems() {
        return numberofItems;
    }

    public double getPrice() {
        return price;
    }

    public String getObjecttype() {
        return objecttype;
    }

    abstract String productDetails();

    public String getBrand(){
        return getBrand();
    }
    public int getWarrantyPeriod(){
        return getWarrantyPeriod();
    }
    public String getSize(){
        return getSize();
    }

    public String getColor(){
        return getColor();
    }

    public int compareTo(Product product){
        return ProductId.compareTo(product.ProductId);
    }
}
