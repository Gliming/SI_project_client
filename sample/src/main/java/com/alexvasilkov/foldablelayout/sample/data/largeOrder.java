package com.alexvasilkov.foldablelayout.sample.data;
import java.util.ArrayList;

public class largeOrder {
    public String id;

    public String user;
    public Integer peopleNum;
    public String addr;
    public ArrayList<order> orders;

    public largeOrder(){}

    public largeOrder(String user, String addr, ArrayList<order> orders){
        this.user = user;
        this.peopleNum = 1;
        this.orders = orders;
        this.addr = addr;
    }

}
