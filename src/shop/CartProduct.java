package shop;

public class CartProduct {

    Long helpId = 0L;
    Long id;

    public Cart cart;
    public Product product;
    //price of one of the product
    public Long price;
    public Long count;
    public CartProductStatus status;

    public CartProduct(Cart cart, Product product, Long count) {
        this.cart = cart;
        this.product = product;
        this.count = count;

        status = CartProductStatus.PENDING;

        id = helpId;
        helpId += 1;
    }
}
