package com.ernext;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ernext.adapter.MycartAdapter;
import com.ernext.classtype.Mycart;
import com.ernext.helperclass.CircularTextView;
import com.ernext.helperclass.Eutils;

import java.util.ArrayList;
import java.util.List;

public class ActivityMyprofile extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    Spinner sp_places;
    PercentRelativeLayout v_searchview;
    CircularTextView tv_cartcount;
    NavigationView navigationView;
    ImageView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile_inc);
        mContext = this;

        initialization();
    }

    private void initialization() {
        sp_places = (Spinner) findViewById(R.id.sp_places);
        v_searchview = (PercentRelativeLayout) findViewById(R.id.v_searchview);
        v_searchview.setVisibility(View.GONE);
        sp_places.setOnItemSelectedListener(this);
        tv_cartcount = (CircularTextView) findViewById(R.id.tv_cartcount);
        tv_cartcount.setSolidColor("#002f49");
        btn_menu = (ImageView) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txt_name = (TextView) headerView.findViewById(R.id.tv_notif_tips_title);
        ImageView ivNavDrawer = (ImageView) findViewById(R.id.iv_product_photo);
        TextView tvName = (TextView) findViewById(R.id.tv_notif_tips_title);
        feedspinner();

    }

    private void feedspinner() {
        List<String> categories = new ArrayList<>();
        categories.add("Freemonte");
        categories.add("Kolkata");
        categories.add("Bangalore");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_places.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == btn_menu) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Eutils.openNavDrawer(id, mContext);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
