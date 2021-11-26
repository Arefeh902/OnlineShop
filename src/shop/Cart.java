package shop;

import functionality.Main;
import users.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class Cart {

    static Long helpId = 1L;
    Long id;

    User client;
    public ArrayList<CartProduct> cartProducts;
    public CartStatus status;

    public Cart(User user) {
        this.client = user;
        this.cartProducts = new ArrayList<>();
        this.status = CartStatus.PENDING;

        this.id = helpId;
        helpId += 1;
    }

    public void addProduct(Product product){
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
        for(CartProduct cartP: this.cartProducts){
            if(cartP.product.equals(product)){
                cartP.decCount();
                return;
            }
        }
        System.out.println("product not found!");
    }

    public void removeProduct(Product product){
        for(CartProduct cartP: cartProducts){
            if(cartP.equals(product)){
                cartProducts.remove(cartP);
                break;
            }
        }
    }

    public void verifyCart(){

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

}
