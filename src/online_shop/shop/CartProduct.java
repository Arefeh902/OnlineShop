package online_shop.shop;

public class CartProduct {

    Long helpId = 0L;
    Long id;

    public Cart cart;
    public Product product;
    //price of one of the product
    public Long price;
    public Long count;
    public CartProductStatus status;

    public CartProduct(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.count = 1L;

        status = CartProductStatus.PENDING;

        id = helpId;
        helpId += 1;
    }

    public Boolean incCount(){
        if(this.count < this.product.inventory){
            count += 1;
            return Boolean.TRUE;
        }
        System.out.println("not enough products!");
        return Boolean.FALSE;
    }

    public void decCount(){
        this.count -= 1;
        if(this.count == 0){
            this.cart.cartProducts.remove(this);
        }
    }
}
