package shop;

import java.util.ArrayList;

public class Cart {

    static Long helpId = 1L;
    Long id;

    ArrayList<CartProduct> products;

    public Cart() {
        this. products = new ArrayList<>();

        this.id = helpId;
        helpId += 1;
    }

    public void addProduct(Product product, Long count){
        if(count > product.inventory){
            System.out.println("Invalid number of Items, Total of " + product.inventory + " are left");
            return;
        }
        CartProduct cartProduct = new CartProduct(this, product, count);
    }
}
