package com.alexvasilkov.foldablelayout.sample.data;
import com.google.gson.Gson;

public class mItem {

    public String id;
    public Integer imageID;
    public String itemName;
    public Integer price;
    public mItem(){}

    public mItem(String itemName, Integer price, Integer imageID){
        this.itemName = itemName;
        this.price = price;
        this.imageID = imageID;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}
