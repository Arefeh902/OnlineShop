package shop;

import functionality.Main;
import users.Seller;

public class Product {

    static Long helpId = 1L;
    Long id;

    Seller seller;
    Long price;
    Long inventory;

    public Product(Long price, Long inventory) {
        this.seller = Main.appData.currentSeller;
        this.price = price;
        this.inventory = inventory;

        id = helpId;
        helpId += 1;
    }
}
