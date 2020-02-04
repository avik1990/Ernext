package com.ernext;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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

import com.ernext.adapter.FeaturedChefAdapter;
import com.ernext.adapter.MycartAdapter;
import com.ernext.classtype.FeaturedChefs;
import com.ernext.classtype.Mycart;
import com.ernext.helperclass.CircularTextView;
import com.ernext.helperclass.Eutils;

import java.util.ArrayList;
import java.util.List;

public class ActivityCart extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    RecyclerView fc_recycler_view;
    private List<Mycart> list_mycart = new ArrayList<>();
    Context mContext;
    Spinner sp_places;
    PercentRelativeLayout v_searchview;
    CircularTextView tv_cartcount;
    NavigationView navigationView;
    ImageView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_inc);
        mContext = this;

        initialization();
    }

    private void initialization() {
        fc_recycler_view = (RecyclerView) findViewById(R.id.fc_recycler_view);
        fc_recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        fc_recycler_view.setItemAnimator(new DefaultItemAnimator());
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
        createList();

        MycartAdapter ca = new MycartAdapter(mContext, list_mycart);
        fc_recycler_view.setAdapter(ca);

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


    private void createList() {
        list_mycart.clear();
        list_mycart.add(new Mycart("1", "Chicken Briyani", ">>Indian >> Chicken", "120", "12", "Rakesh Samanta", "http://i.ndtvimg.com/i/2015-05/chicken-curry_625x350_61430904532.jpg", "3"));
        list_mycart.add(new Mycart("2", "Chicken Payaja", ">>Indian >> Chicken", "220", "2", "Raj Singh", "http://www.alwaysfoodie.com/wp-content/uploads/2012/07/indian_chicken_dishes_njhzq.jpg", "2"));
        list_mycart.add(new Mycart("3", "Chilli Chiken", ">>Indian >> Chicken", "230", "2", "Himesh", "http://finedininglovers.cdn.crosscast-system.com/BlogPost/l_4328_StockFood-00410320cut.jpg", "4"));
        list_mycart.add(new Mycart("4", "Chicken Bharta", ">>Indian >> Chicken", "123", "3", "Prosenjit Paul", "https://6d1bdf0e0edb084eae10-5bfabe5484726969ac662c6d377e2f3c.ssl.cf2.rackcdn.com/themes/img/north-indian-food4.jpg", "1"));
        list_mycart.add(new Mycart("5", "Chicken Special", ">>Indian >> Chicken", "890", "4", "Subroto Roy", "http://i.ndtvimg.com/i/2015-01/teekha-murgh_625x350_41421325402.jpg", "2"));
        list_mycart.add(new Mycart("6", "American Chicken", ">>Indian >> Chicken", "340", "5", "Ramesh", "http://www.showmethecurry.com/wp/wp-content/uploads/dsc00119.JPG", "1"));
        list_mycart.add(new Mycart("7", "Chicken Pasta", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Mutton Pasta", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Briyani", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Chicken Roast", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Chicken", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Chicken Soup", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));
        list_mycart.add(new Mycart("7", "Chicken Mix", ">>Indian >> Chicken", "223", "3", "Suresh", "https://s-media-cache-ak0.pinimg.com/736x/b4/12/5a/b4125aed52c1bd23f17a1cfe5284b7ae.jpg", "2"));

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
