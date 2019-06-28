package com.alexvasilkov.foldablelayout.sample.activities.fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.sample.R;
import com.alexvasilkov.foldablelayout.sample.activities.BaseFragment;
import com.alexvasilkov.foldablelayout.sample.activities.BottomBar;
import com.alexvasilkov.foldablelayout.sample.activities.UnfoldableDetailsActivity;
import com.alexvasilkov.foldablelayout.sample.data.HttpClient;
import com.alexvasilkov.foldablelayout.sample.data.mItem;
import com.alexvasilkov.foldablelayout.sample.data.largeOrder;
import com.alexvasilkov.foldablelayout.sample.data.order;
import com.alexvasilkov.foldablelayout.sample.data.orderItem;
import com.alexvasilkov.foldablelayout.sample.items.mallItemsAdapter;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.alexvasilkov.foldablelayout.sample.data.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends BaseFragment implements mallItemsAdapter.Unfordable {
    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;
    private mallItemsAdapter mallItems_adapter;
    private ImageView btn_share;
    private ImageView btn_tag;
    private f1_clickListener btn_click_listener;
    private Button btn_submit;
    private static boolean is_check = false;
    private View tmp;
    private BottomBar bottom_bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contacts_view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, container, false);
        tmp=contacts_view;
        HttpClient.init();

        ListView listView = (ListView) contacts_view.findViewById(R.id.list_view);

        Fragment1 fragment1 = this;
        UnfoldableDetailsActivity main = (UnfoldableDetailsActivity) this.getActivity();
        mallItems_adapter = new mallItemsAdapter(main, fragment1);
        listView.setAdapter(mallItems_adapter);

        listTouchInterceptor = contacts_view.findViewById(R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = contacts_view.findViewById(R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        unfoldableView = (UnfoldableView) contacts_view.findViewById(R.id.unfoldable_view);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        unfoldableView.setFoldShading(new GlanceFoldShading(glance));
        //

        //
        bottom_bar = (BottomBar) Views.find(this.getActivity(), R.id.bottom_bar);



        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                bottom_bar.setVisibility(View.INVISIBLE);
                detailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                bottom_bar.setVisibility(View.VISIBLE);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });

        return contacts_view;
        //return LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, container, false);
    }

    //
    protected class f1_clickListener implements View.OnClickListener {
        private Context m_context;
        private Activity activity;
        private Integer id;

        public f1_clickListener(Context m_context, Activity activity,Integer id) {
            this.m_context = m_context;
            this.activity = activity;
            this.id=id;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_OK_dialog:
                    Toast.makeText(activity,"购买成功",Toast.LENGTH_LONG).show();
                    orderItem orderItem = new orderItem("5d1330314aecf22d50effc6b",3,id);
                    ArrayList<orderItem> itemList = new ArrayList<orderItem>();
                    itemList.add(orderItem);
                    order order = new order("zsj", itemList);
                    String addr = "sjtu";
                    ArrayList<order> orders = new ArrayList<order>();
                    orders.add(order);
                    largeOrder largeOrder= new largeOrder("zsj",addr,orders);
                    HttpClient.mall_largeOrders.add(largeOrder);
                    HttpClient.mall_orders.add(order);
                    Toast.makeText(activity,"购买成功",Toast.LENGTH_LONG).show();



                    break;
                default:
                    break;
            }

        }
    }
    //

    @Override
    public void onVisible() {
//        HttpClient.getUser(1544704862401l,(data)->{
//            User user = (User) data;
//            if(user==null) {
//                Log.d("HttpClient","get user failed.");
//                HttpClient.user = new User();
//                return;
//            }
//            Log.d("HttpClient",user.toString());
//            if(user.getCards()==null)
//                user.cards = new ArrayList<Card>();
//            HttpClient.user = user;
//            //is_check = true;
//        });
        if (mallItems_adapter != null) {
            mallItems_adapter.resetCards();
            mallItems_adapter.notifyDataSetChanged();
        }
//        while (is_check == true) {
//            card_adapter.resetCards();
//            card_adapter.notifyDataSetChanged();
//        }
//        is_check = false;
    }

    @Override
    public void openDetails(View coverView, mItem item) {
        final ImageView image = Views.find(detailsLayout, R.id.details_image);
        final TextView title = Views.find(detailsLayout, R.id.details_title);
        final TextView description = Views.find(detailsLayout, R.id.details_text);
        GlideHelper.loadPaintingImage(image, item.imageID);
        btn_click_listener = new f1_clickListener(this.getContext(), this.getActivity(),item.imageID);
        btn_submit= (Button) tmp.findViewById(R.id.btn_OK_dialog);
        btn_submit.setOnClickListener(btn_click_listener);
        title.setText(item.itemName);
        SpannableBuilder builder = new SpannableBuilder(this.getActivity());
        if (item.price != null) {
            builder
                    .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                    .append("价格").append(": ")
                    .clearStyle()
                    .append(item.price.toString()+"元/例").append("\n");
        }
        description.setText(builder.build());
        unfoldableView.unfold(coverView, detailsLayout);
    }

}
