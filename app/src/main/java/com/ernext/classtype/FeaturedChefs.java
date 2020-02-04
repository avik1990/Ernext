package com.ernext.classtype;

/**
 * Created by Avik on 11-01-2017.
 */

public class FeaturedChefs {
    String c_id;
    String c_name;
    String c_rates;
    String c_likes;
    String c_images;

    public FeaturedChefs(String c_id, String c_name, String c_rates, String c_likes, String c_images) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_rates = c_rates;
        this.c_likes = c_likes;
        this.c_images = c_images;
    }

    public String getC_images() {
        return c_images;
    }

    public void setC_images(String c_images) {
        this.c_images = c_images;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_rates() {
        return c_rates;
    }

    public void setC_rates(String c_rates) {
        this.c_rates = c_rates;
    }

    public String getC_likes() {
        return c_likes;
    }

    public void setC_likes(String c_likes) {
        this.c_likes = c_likes;
    }
}
