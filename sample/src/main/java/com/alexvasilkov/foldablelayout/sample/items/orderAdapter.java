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
import com.alexvasilkov.foldablelayout.sample.data.order;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;

public class orderAdapter extends ItemsAdapter<order, orderAdapter.ViewHolder> implements View.OnClickListener {

    public Painting [] paintings;

    public orderAdapter(Context context) {
        setItemsList(HttpClient.mall_orders);
        paintings = Painting.getAllPaintings(context.getResources());
    }
    public void resetCards(){
        setItemsList(HttpClient.mall_orders);
    }

    @Override
    protected orderAdapter.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final orderAdapter.ViewHolder holder = new orderAdapter.ViewHolder(parent);
        holder.image.setOnClickListener(this);
       // holder.btn_add.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(orderAdapter.ViewHolder holder, int position) {
        final order order = getItem(position);

        holder.image.setTag(R.id.list_item_image, order);
     //   holder.btn_add.setTag(R.id.list_item_image, order);

        int imgId = paintings[1 % paintings.length].getImageId();
        GlideHelper.loadPaintingImage(holder.image, order.itemList.get(0).imageID);
        holder.title.setText(order.user);
    }

    @Override
    public void onClick(View view) {
//        final Card item = (Card) view.getTag(R.id.list_item_image);
//        final Activity activity = ContextHelper.asActivity(view.getContext());
//
//        switch (view.getId()) {
//            case R.id.btn_add_new_card:
//                break;
//            case R.id.list_item_image:
//                if (activity instanceof UnfoldableDetailsActivity) {
//                    unfordable.openDetails(view, item);
//                } else if (activity instanceof FoldableListActivity) {
//                    Toast.makeText(activity, item.getName(), Toast.LENGTH_SHORT).show();
//                } else if (activity instanceof RecommondUserActivity) {
//                    ((RecommondUserActivity) activity).openDetails(view, item);
//                }
//                break;
//            default:
//                break;
//        }
    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;
     //   final Button btn_add;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.list_item2));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
         //   btn_add = Views.find(itemView, R.id.btn_add_new_card);
        }
    }
}
