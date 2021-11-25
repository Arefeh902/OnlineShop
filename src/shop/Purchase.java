package shop;

public class Purchase {

    Long helpId = 1L;
    Long id;

    Cart cart;
    Long productCount;
    Long totalPrice;
    PurchaseStatus status;

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

}
