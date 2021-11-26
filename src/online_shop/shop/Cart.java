package online_shop.shop;

import online_shop.functionality.Main;
import online_shop.users.User;

import java.util.ArrayList;

public class Cart {

    static Long helpId = 1L;
    Long id;

    public User user;
    public ArrayList<CartProduct> cartProducts;
    public CartStatus status;

    public Cart(User user) {
        this.user = user;
        this.cartProducts = new ArrayList<>();
        this.status = CartStatus.PENDING;

        this.id = helpId;
        helpId += 1;
    }

    public void addProduct(Product product){
        if(status == CartStatus.PURCHASED)
            return;
        for(CartProduct cartP: this.cartProducts){
            if(cartP.product.equals(product)){
                cartP.incCount();
                return;
            }
        }
        if(product.inventory == 0L){
            System.out.println("Invalid number of Items, Total of " + product.inventory + " are left");
            return;
        }
        CartProduct cartProduct = new CartProduct(this, product);
        cartProducts.add(cartProduct);
    }

    public void removeOne(Product product){
        if(status == CartStatus.PURCHASED)
            return;
        for(CartProduct cartP: this.cartProducts){
            if(cartP.product.equals(product)){
                cartP.decCount();
                return;
            }
        }
        System.out.println("product not found!");
    }

    public void verifyCart(){
        for(CartProduct cartP: cartProducts){
            if(cartP.count > cartP.product.inventory){
                System.out.println("validation error");
                System.out.println("product is removed");
                cartProducts.remove(cartP);
            }
        }
    }

    public CartProduct getCartProduct(Product product){
        for(CartProduct cartP: cartProducts){
            if(cartP.product.equals(product)){
                return cartP;
            }
        }
        return null;
    }

    public Boolean hasProduct(Product product){
        for(CartProduct cartP: cartProducts){
            if(cartP.product.equals(product)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Purchase purchase(){
        status = CartStatus.PURCHASED;
        Purchase p = Main.appData.createPurchase(this);
        Main.appData.currentUser.currentCart = Main.appData.createCart(Main.appData.currentUser);
        return p;
    }

}
