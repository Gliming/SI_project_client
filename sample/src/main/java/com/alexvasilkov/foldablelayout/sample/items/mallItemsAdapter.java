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
import com.alexvasilkov.foldablelayout.sample.data.mItem;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;

public class mallItemsAdapter extends ItemsAdapter<mItem, mallItemsAdapter.ViewHolder>
        implements View.OnClickListener {

    private Unfordable unfordable;
    public Painting [] paintings;


    public mallItemsAdapter(Context context, Unfordable unfordable) {
        setItemsList(HttpClient.mall_items);
        this.unfordable = unfordable;
        paintings = Painting.getAllPaintings(context.getResources());
    }
    public void resetCards(){
        setItemsList(HttpClient.mall_items);
    }

    @Override
    protected mallItemsAdapter.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        final mallItemsAdapter.ViewHolder holder = new mallItemsAdapter.ViewHolder(parent);
        holder.image.setOnClickListener(this);
        return holder;
    }

    @Override
    protected void onBindHolder(mallItemsAdapter.ViewHolder holder, int position) {
        final mItem item = getItem(position);

        holder.image.setTag(R.id.list_item_image, item);

        int imgId = paintings[1 % paintings.length].getImageId();
        GlideHelper.loadPaintingImage(holder.image, item.imageID);
        holder.title.setText(item.itemName);

//        if (unfordable instanceof View.OnClickListener){
//            holder.btn_add.setVisibility(View.VISIBLE);
//            holder.btn_add.setOnClickListener(((View.OnClickListener) unfordable));
//        }
//        else{
//            holder.btn_add.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public void onClick(View view) {
        final mItem mItem = (mItem) view.getTag(R.id.list_item_image);
        final Activity activity = ContextHelper.asActivity(view.getContext());

        switch (view.getId()) {
            case R.id.btn_add_new_card:
                break;
            case R.id.list_item_image:
                if (activity instanceof UnfoldableDetailsActivity) {
                    unfordable.openDetails(view, mItem);
                }
                break;
            default:
                break;
        }
    }

    static class ViewHolder extends ItemsAdapter.ViewHolder {
        final ImageView image;
        final TextView title;
//        final Button btn_add;

        ViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, R.layout.list_item2));
            image = Views.find(itemView, R.id.list_item_image);
            title = Views.find(itemView, R.id.list_item_title);
//            btn_add = Views.find(itemView, R.id.btn_add_new_card);
        }
    }

    public interface Unfordable{
        public void openDetails(View coverView, mItem card);
    }

}
