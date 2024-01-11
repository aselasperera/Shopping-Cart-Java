import java.util.ArrayList;
import java.util.Collections;

public class ShoppingCart {
    private ArrayList<Product> productList;

    public ShoppingCart() {
        this.productList =new ArrayList<>();
    }

    public  void addProduct(Product product) {
        productList.add(product);
    }

    public ArrayList<Product> getProductList() {
        Collections.sort(productList);
        return productList;
    }

    public void deleteProduct(Product product){
        productList.remove(product);
    }



}
