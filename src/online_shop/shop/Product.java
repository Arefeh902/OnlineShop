package online_shop.shop;

import online_shop.functionality.Main;
import online_shop.users.Seller;

public class Product {

    static Long helpId = 1L;
    Long id;

    public Seller seller;
    public String name;
    public Long price;
    public Long inventory;

    public ProductStatus status;

    public Product(String name, Long price, Long inventory) {
        this.name = name;
        this.seller = Main.appData.currentSeller;
        this.price = price;
        this.inventory = inventory;
        this.status = ProductStatus.ACTIVE;

        id = helpId;
        helpId += 1;
    }

    public void editName(String name){
        this.name = name;
    }

    public void editPrice(Long price){
        this.price = price;
    }

    public void editInventory(Long inventory){
        this.inventory = inventory;
    }

    //not enough time to add feature
    public void archiveProduct(){
        this.status = ProductStatus.ARCHIVED;
    }

    //not enough time to add feature
    public void deleteProduct(){
        this.status = ProductStatus.DELETED;
    }

    @Override
    public String toString() {
        return  id +
                ". " + name +
                "  " + price +
                " " + inventory;
    }
}
