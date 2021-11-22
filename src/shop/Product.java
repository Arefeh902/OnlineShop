package shop;

import functionality.Main;
import users.Seller;

public class Product {

    static Long helpId = 1L;
    Long id;

    Seller seller;
    String name;
    Long price;
    Long inventory;

    ProductStatus status;

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

    public void archiveProduct(){
        this.status = ProductStatus.ARCHIVED;
    }

    public void deleteProduct(){
        this.status = ProductStatus.DELETED;
    }

    public static void allProducts(){
        System.out.println("id name price inventory");
        for(Product product: Main.appData.products){
            System.out.println(product.toString());
        }
    }

    @Override
    public String toString() {
        return  id +
                ". " + name +
                "  " + price +
                " " + inventory;
    }
}
