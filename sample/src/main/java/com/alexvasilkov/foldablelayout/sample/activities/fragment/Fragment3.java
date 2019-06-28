package com.alexvasilkov.foldablelayout.sample.activities.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.sample.R;
import com.alexvasilkov.foldablelayout.sample.activities.BaseFragment;
import com.alexvasilkov.foldablelayout.sample.data.HttpClient;
import com.alexvasilkov.foldablelayout.sample.data.largeOrder;
import com.alexvasilkov.foldablelayout.sample.data.mItem;
import com.alexvasilkov.foldablelayout.sample.items.largeOrderAdapter;
import com.alexvasilkov.foldablelayout.sample.items.orderAdapter;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;


import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends BaseFragment implements View.OnClickListener {
    private orderAdapter orderAdapter;
    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;
    private ListView listView;

    private Button btn_submit;
    private Button btn_cancel;

    //private ru_clickListener btn_click_listener;
    //private List<largeOrder> cards = new ArrayList<>();
    public Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contacts_view = LayoutInflater.from(getActivity()).inflate(R.layout.recommond_user2, container, false);

        listView = (ListView) contacts_view.findViewById(R.id.list_view);
        orderAdapter = new orderAdapter(this.getContext());
        orderAdapter.setItemsList(HttpClient.mall_orders);
        listView.setAdapter(orderAdapter);

        listTouchInterceptor = contacts_view.findViewById(R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = contacts_view.findViewById(R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        return contacts_view;
    }

    @Override
    public void onClick(View view) {
//        Card new_card = (Card) view.getTag(R.id.list_item_image);
//        Log.d("add recommoned card", "add recommond card");
//        if(new_card != null) {
//            Log.d("add recommoned card", "add recommond card");
//            cards.remove(new_card);
//            card_adapter.notifyDataSetChanged();
//            List<Card> self_card_list = HttpClient.user.getCards();
//            for (Card card_item : self_card_list){
//                if (card_item.getId() == new_card.getId()){
//                    Toast.makeText(this.getActivity(), "你已经添加过该名片", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//
//            Log.d("card_check",new_card.toString());
//            HttpClient.user.setCards(self_card_list);
//            Log.d("card_check",HttpClient.user.toString());
//            HttpClient.updateUser(HttpClient.user, (data_1)->{
//                if(data_1 == null) {
//                    Log.d("HttpClient","update user  failed after adding new card!");
//                    return;
//                }
//                else{
//                    self_card_list.add((Card)new_card);
//                }
//            });
//        }
//        else{
//            Log.d("HttpClient","add new card failed!");
//        }
//        return;
    }

}
