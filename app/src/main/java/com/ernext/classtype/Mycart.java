package com.ernext.classtype;

/**
 * Created by Avik on 23-01-2017.
 */

public class Mycart {

    String p_id;
    String p_name;
    String p_subcategory;
    String p_price;
    String p_quantity;
    String p_checfname;
    String p_image;
    String p_rating;

    public Mycart(String p_id, String p_name, String p_subcategory, String p_price, String p_quantity, String p_checfname, String p_image, String p_rating) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_subcategory = p_subcategory;
        this.p_price = p_price;
        this.p_quantity = p_quantity;
        this.p_checfname = p_checfname;
        this.p_image = p_image;
        this.p_rating = p_rating;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_subcategory() {
        return p_subcategory;
    }

    public void setP_subcategory(String p_subcategory) {
        this.p_subcategory = p_subcategory;
    }

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
        this.p_price = p_price;
    }

    public String getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity = p_quantity;
    }

    public String getP_checfname() {
        return p_checfname;
    }

    public void setP_checfname(String p_checfname) {
        this.p_checfname = p_checfname;
    }

    public String getP_rating() {
        return p_rating;
    }

    public void setP_rating(String p_rating) {
        this.p_rating = p_rating;
    }
}
