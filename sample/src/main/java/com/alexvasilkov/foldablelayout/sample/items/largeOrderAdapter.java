package com.alexvasilkov.foldablelayout.sample.items;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.ContextHelper;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.sample.R;
import com.alexvasilkov.foldablelayout.sample.activities.FoldableListActivity;
import com.alexvasilkov.foldablelayout.sample.activities.UnfoldableDetailsActivity;
import com.alexvasilkov.foldablelayout.sample.data.HttpClient;
import com.alexvasilkov.foldablelayout.sample.data.largeOrder;
import com.alexvasilkov.foldablelayout.sample.data.mItem;
import com.alexvasilkov.foldablelayout.sample.data.order;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;

public class largeOrderAdapter extends ItemsAdapter<largeOrder, largeOrderAdapter.ViewHolder> implements View.OnClickListener {

    public Painting [] paintings;


    public largeOrderAdapter(Context context) {
        setItemsList(HttpClient.mall_largeOrders);
        paintings = Painting.getAllPaintings(context.getResources());
    }
    public void resetCards(){
        setItemsList(HttpClient.mall_largeOrders);
    }

    @Override
    protected largeOrderAdapter.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final largeOrderAdapter.ViewHolder holder = new largeOrderAdapter.ViewHolder(parent);
        holder.image.setOnClickListener(this);
        holder.btn_add.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(largeOrderAdapter.ViewHolder holder, int position) {
        final largeOrder largeOrder = getItem(position);

        holder.image.setTag(R.id.list_item_image, largeOrder);
        holder.btn_add.setTag(R.id.list_item_image, largeOrder);

        int imgId = paintings[1 % paintings.length].getImageId();
        GlideHelper.loadPaintingImage(holder.image, largeOrder.orders.get(0).itemList.get(0).imageID);
        holder.title.setText("用户:"+largeOrder.user+ "    地址:"+largeOrder.addr);
    }

    @Override
    public void onClick(View view) {

        final largeOrder item = (largeOrder) view.getTag(R.id.list_item_image);
        final Activity activity = ContextHelper.asActivity(view.getContext());
//
        switch (view.getId()) {
            case R.id.btn_add_new_card:
                order order = new order("zsj", item.orders.get(0).itemList);
                item.orders.add(order);
                HttpClient.mall_orders.add(order);
                Toast.makeText(activity,"拼团成功",Toast.LENGTH_LONG).show();


                break;

            default:
                break;
        }
    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;
        final Button btn_add;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.list_item));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
            btn_add = Views.find(itemView, R.id.btn_add_new_card);
        }
    }
}
