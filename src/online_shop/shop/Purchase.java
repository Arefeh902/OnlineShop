package online_shop.shop;

public class Purchase {

    Long helpId = 1L;
    Long id;

    public Cart cart;
    public Long productCount;
    public Long totalPrice;
    public PurchaseStatus status;

    public Purchase(Cart cart) {
        this.cart = cart;
        //could be calculated in the for loop below
        productCount = Integer.toUnsignedLong(cart.cartProducts.size());
        totalPrice = 0L;
        for(CartProduct cartP: cart.cartProducts){
            cartP.price = cartP.product.price;
            totalPrice += cartP.count * cartP.price;
            cartP.product.inventory -= cartP.count;
        }
        status = PurchaseStatus.PENDING;

        id = helpId;
        helpId += 1;
    }

    public Long getId(){
        return id;
    }

}
