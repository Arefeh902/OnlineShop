package shop;

public class CartProduct {

    Long helpId = 0L;
    Long id;

    Cart cart;
    Product product;
    //price of one of the product
    Long price;
    Long count;
    CartProductStatus status;

    public CartProduct(Cart cart, Product product, Long count) {
        this.cart = cart;
        this.product = product;
        this.count = count;

        status = CartProductStatus.NOT_PURCHASED;

        id = helpId;
        helpId += 1;
    }
}
