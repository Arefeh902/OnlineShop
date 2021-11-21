package shop;

public class CartProduct {
    Cart cart;
    Product product;
    Long prince;
    Long count;

    public CartProduct(Cart cart, Product product, Long count) {
        this.cart = cart;
        this.product = product;
        this.count = count;
    }
}
