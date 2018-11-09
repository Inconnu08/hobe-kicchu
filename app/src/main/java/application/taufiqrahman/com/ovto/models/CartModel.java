package application.taufiqrahman.com.ovto.models;

import java.util.List;

/**
 * Created by Taufiq on 8/13/2018.
 */

public class CartModel {

    private Long user_id;
    private Long table_number;
    private List<Cart> carts = null;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getTable_number() {
        return table_number;
    }

    public void setTable_number(Long table_number) {
        this.table_number = table_number;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public static class Cart {

        private int menu_id;
        private String genericname;
        private String foodtitle;
        private String default_Menu_picture;
        private int price;
        private int quantity;

        public Cart(int menu_id, String genericname, String foodtitle, String default_Menu_picture, int price, int quantity) {
            this.menu_id = menu_id;
            this.genericname = genericname;
            this.foodtitle = foodtitle;
            this.default_Menu_picture = default_Menu_picture;
            this.price = price;
            this.quantity = quantity;
        }

        public int getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(int menu_id) {
            this.menu_id = menu_id;
        }

        public String getGenericname() {
            return genericname;
        }

        public void setGenericname(String genericname) {
            this.genericname = genericname;
        }

        public String getFoodtitle() {
            return foodtitle;
        }

        public void setFoodtitle(String foodtitle) {
            this.foodtitle = foodtitle;
        }

        public String getDefault_Menu_picture() {
            return default_Menu_picture;
        }

        public void setDefault_Menu_picture(String default_Menu_picture) {
            this.default_Menu_picture = default_Menu_picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
