import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    public static List<mItem> mall_items;
    public static List<order> mall_orders;
    public static List<largeOrder> mall_largeOrders;
    static String basic_url1 ="http://127.0.0.1:8764";
    static String basic_url2 ="http://127.0.0.1:8765";

    public interface OnDataReceived {
        void callback(Object data);
    }

    public static void main (String[] args){
        mall_items = new ArrayList<mItem>();
        mall_largeOrders = new ArrayList<largeOrder>();
        mall_orders = new ArrayList<order>();

        //add_to_large_order
//        orderItem orderItem = new orderItem("5d1330314aecf22d50effc6b",5);
//        ArrayList<orderItem> itemList = new ArrayList<orderItem>();
//        itemList.add(orderItem);
//        order order = new order("zsjxxx", itemList);
//        HttpClient.add_to_large_order("5d13768d51288c4338aaa70d", order, (data)->{
//            if(data==null)
//                return;
//            System.out.println(data.toString());
//            order.id = data.toString();
//        });

        //get orders by user
//        HttpClient.get_orders_by_user("hg", (data)->{
//            if(data==null)
//                return;
//            mall_orders = (ArrayList<order>)data;
//            for (int i = 0; i < mall_orders.size(); i++){
//                System.out.println(mall_orders.get(i).user);
//            }
//        });


        //get orders
        HttpClient.get_orders((data)->{
            if(data==null)
                return;
            mall_orders = (ArrayList<order>)data;
            for (int i = 0; i < mall_orders.size(); i++){
                System.out.println(mall_orders.get(i).user);
            }
        });

        //get large_orders
//        HttpClient.get_large_orders((data)->{
//            if(data==null)
//                return;
//            mall_largeOrders = (ArrayList<largeOrder>)data;
//            for (int i = 0; i < mall_largeOrders.size(); i++){
//                System.out.println(mall_largeOrders.get(i).peopleNum);
//                System.out.println(mall_largeOrders.get(i).addr);
//            }
//        });


        //get items
//        HttpClient.get_items((data)->{
//            if(data==null)
//                return;
//            mall_items = (ArrayList<mItem>)data;
//            for (int i = 0; i < mall_items.size(); i++){
//                System.out.println(mall_items.get(i).itemName);
//                System.out.println(mall_items.get(i).price);
//            }
//        });

        //add items
//        mItem mItem = new mItem("ooo", 2);
//        HttpClient.add_items(mItem, (data)->{
//            if(data==null)
//                return;
//            mItem.id = data.toString();
//        });


        //buy items
//        orderItem orderItem = new orderItem("5d1330314aecf22d50effc6b",3);
//        ArrayList<orderItem> itemList = new ArrayList<orderItem>();
//        itemList.add(orderItem);
//        order order = new order("zsj", itemList);
//        String addr = "sjtu";
//
//        HttpClient.buy(addr, order, (data)->{
//            if(data==null)
//                return;
//            System.out.println(data.toString());
//        });

//        mItem item1 = new mItem("apple", 3);
//        mItem item2 = new mItem("banana",4);
//        mall_items.add(item1);
//        mall_items.add(item2);
//

//        HttpClient.getItems((data)->{
//            if(data==null)
//                return;
//            List<item> items = (List<item>) data;
//        });

    }

    public static void get_orders_by_user(String username, OnDataReceived onDataReceived){
        String url = basic_url2 + "/get_orders_by_user?username=" + username;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get orders by user");
                System.out.println(e);
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);

                Type listType = new TypeToken<ArrayList<order>>(){}.getType();
                ArrayList<order> orders = (ArrayList<order>) new Gson().fromJson(res,listType);
                onDataReceived.callback(orders);
            }
        });

    }

    public static void get_orders(OnDataReceived onDataReceived){
        String url = basic_url2 + "/get_orders";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get orders ");
                System.out.println(e);
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);

                Type listType = new TypeToken<ArrayList<order>>(){}.getType();
                ArrayList<order> orders = (ArrayList<order>) new Gson().fromJson(res,listType);
                onDataReceived.callback(orders);
            }
        });

    }


    public static void get_large_orders(OnDataReceived onDataReceived){
        String url = basic_url2 + "/get_large_orders";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get largeOrders ");
                System.out.println(e);
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);

                Type listType = new TypeToken<ArrayList<largeOrder>>(){}.getType();
                ArrayList<largeOrder> largeOrders = (ArrayList<largeOrder>) new Gson().fromJson(res,listType);
                onDataReceived.callback(largeOrders);
            }
        });

    }


    public static void get_items(OnDataReceived onDataReceived){
        String url = basic_url2 + "/get_items";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when get Items ");
                System.out.println(e);
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);

                Type listType = new TypeToken<ArrayList<mItem>>(){}.getType();
                ArrayList<mItem> items = (ArrayList<mItem>) new Gson().fromJson(res,listType);
                onDataReceived.callback(items);
            }
        });

    }

    public static void buy(String addr, order order, OnDataReceived onDataReceived){
        String url=basic_url1+"/buy?addr=" + addr;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(RequestBody.create(mediaType,order.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when buy items.");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                //User user = (User) new Gson().fromJson(res,User.class);
                onDataReceived.callback(res);
            }
        });
    }

    public static void add_items(mItem newItem, OnDataReceived onDataReceived){
        String url=basic_url1+"/add_item?itemName="+ newItem.itemName + "&price=" + newItem.price;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add_items ");
                System.out.println(e);
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                onDataReceived.callback(res);
            }
        });
    }

    public static void add_to_large_order(String largeOrderId, order order, OnDataReceived onDataReceived){
        String url=basic_url1+"/add_to_large_order?id=" + largeOrderId;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(2000,TimeUnit.MILLISECONDS).build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        final Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/json")
                .post(RequestBody.create(mediaType,order.toString()))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "failed when add_to_large_order");
                onDataReceived.callback(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                System.out.println( "onResponse: " + res);
                onDataReceived.callback(res);
            }
        });
    }


}

