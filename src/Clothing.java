public class Clothing extends Product {

    private String size;
    private String color;


    public Clothing(String productId, String productName, int numberofItems, double price, String objecttype, String size, String color) {
        super(productId, productName, numberofItems, price, objecttype);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }



    @Override
    public String productDetails() {
        return (getProductId()+"|"+getProductName()+"|"+getNumberofItems()+"|"+getPrice()+"|"+getSize()+"|"+getColor()+"|"+getObjecttype());
    }
}
