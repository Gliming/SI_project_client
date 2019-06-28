package com.alexvasilkov.foldablelayout.sample.data;
import com.google.gson.Gson;

import java.util.ArrayList;

public class order {

    public String id;

    public String user;
    public ArrayList<orderItem> itemList;

    public order(){}

    public order(String user, ArrayList<orderItem> item_list){
        this.user = user;
        this.itemList = item_list;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}


