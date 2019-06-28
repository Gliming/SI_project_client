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
import com.alexvasilkov.foldablelayout.sample.items.largeOrderAdapter;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;


import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends BaseFragment implements View.OnClickListener {
    private largeOrderAdapter largeOrderAdapter;
    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;
    private ListView listView;

    private Button btn_add;
    private Button btn_cancel;
    public Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View contacts_view = LayoutInflater.from(getActivity()).inflate(R.layout.recommond_user, container, false);

        listView = (ListView) contacts_view.findViewById(R.id.list_view);
        largeOrderAdapter = new largeOrderAdapter(this.getContext());
        largeOrderAdapter.setItemsList(HttpClient.mall_largeOrders);
        listView.setAdapter(largeOrderAdapter);

        listTouchInterceptor = contacts_view.findViewById(R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = contacts_view.findViewById(R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        return contacts_view;
    }
    @Override
    public void onClick(View view) {

    }


}
