public class Electronics extends Product{

    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID, String productName, int numberOfItems, double price, String objectType, String brand, int warrantyPeriod) {
        super(productID, productName, numberOfItems, price, objectType);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public String productDetails() {
        return(getProductId()+"|"+getProductName()+"|"+getNumberofItems()+"|"+getPrice()+"|"+getBrand()+"|"+getWarrantyPeriod()+"|"+getObjecttype());
    }
}
