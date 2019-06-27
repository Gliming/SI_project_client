import com.google.gson.Gson;

public class mItem {

    public String id;

    public String itemName;
    public Integer price;
    public mItem(){}

    public mItem(String itemName, Integer price){
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}
