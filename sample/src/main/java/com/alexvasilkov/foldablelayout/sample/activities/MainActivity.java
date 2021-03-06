package com.alexvasilkov.foldablelayout.sample.activities;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.ui.Views;
import com.alexvasilkov.foldablelayout.sample.R;
import com.alexvasilkov.foldablelayout.sample.items.Painting;
import com.alexvasilkov.foldablelayout.sample.utils.GlideHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ListView.OnItemClickListener {
    private static final int PERMISSIONS_REQUEST_OPEN_ALBUM = 1;
    private static final int PERMISSIONS_REQUEST_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GlideHelper.init(getResources());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAuthority();

        ListView listView = Views.find(this, R.id.main_list);
        listView.setAdapter(getSampleAdapter());
        listView.setOnItemClickListener(this);

        Painting [] paintings = Painting.getAllPaintings(this.getResources());
        for(Painting p:paintings) {
            Log.d("HttpClient", "pid:" + p.getImageId());
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityInfo info = (ActivityInfo) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, info.name));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    public void getAuthority(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_OPEN_ALBUM);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_OPEN_ALBUM || requestCode == PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //授权成功
                Toast.makeText(this, "Permission Passed", Toast.LENGTH_SHORT).show();

            } else {
                //授权失败
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BaseAdapter getSampleAdapter() {
        List<ActivityInfo> items = new ArrayList<>();

        try {
            ActivityInfo[] activitiesInfo = getPackageManager()
                    .getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES).activities;

            for (ActivityInfo info : activitiesInfo) {
                if (!getClass().getName().equals(info.name)) {
                    items.add(info);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return new SampleAdapter(items);
    }

    private static class SampleAdapter extends ItemsAdapter<ActivityInfo, ItemViewHolder> {

        SampleAdapter(List<ActivityInfo> list) {
            setItemsList(list);
        }

        @Override
        protected ItemViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(parent);
        }

        @Override
        protected void onBindHolder(ItemViewHolder holder, int position) {
            final ActivityInfo info = getItem(position);

            if (TextUtils.isEmpty(info.nonLocalizedLabel)) {
                holder.text.setText("hehe");
            } else {
                holder.text.setText(info.nonLocalizedLabel);
            }
        }

    }

    private static class ItemViewHolder extends ItemsAdapter.ViewHolder {
        final TextView text;

        ItemViewHolder(ViewGroup parent) {
            super(Views.inflate(parent, android.R.layout.simple_list_item_1));
            text = (TextView) itemView;
        }
    }

}
