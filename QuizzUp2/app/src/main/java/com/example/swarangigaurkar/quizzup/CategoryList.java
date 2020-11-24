package com.example.swarangigaurkar.quizzup;

public class CategoryList
{

    private String category;

    private  String rating;

    private  int image;




    public CategoryList( String category, String rating,int image) {

        this.category = category;
        this.rating = rating;
        this.image=image;

    }


    public String getCategory() {
        return category;
    }

    public String getrating() {
        return rating;
    }

    public int getImage(){return image;}

}

