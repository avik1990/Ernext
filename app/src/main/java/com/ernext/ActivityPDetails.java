package com.ernext;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ernext.adapter.SlidingAdapter_details;
import com.ernext.fragments.Tab1;
import com.ernext.fragments.Tab2;
import com.ernext.fragments.Tab3;
import com.ernext.fragments.Tab4;
import com.ernext.helperclass.CircularTextView;
import com.ernext.helperclass.Eutils;
import com.ernext.helperclass.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityPDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private static ViewPager mPager;
    private static int NUM_PAGES = 0;
    private static final String[] IMAGES = {"http://weneedfun.com/wp-content/uploads/2015/10/Beautiful-Food-Photos-1.jpg",
            "http://www.indianfoodforever.com/images/indian-food-platter.jpg",
            "http://thekashmirwalla.com/wp-content/uploads/2015/04/209_1beef_kabobs1.jpg",
            "https://i.ytimg.com/vi/uDnd_C8Hkp8/maxresdefault.jpg"};
    private static final String[] SLIDER_TITLE = {"Chinese",
            "Indian",
            "Korean",
            "Ameriacan"};
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    private ArrayList<String> TitleArray = new ArrayList<String>();
    Context mContext;
    Spinner sp_places;
    RelativeLayout iv_hottestfood;
    CircularTextView tv_cartcount;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NavigationView navigationView;
    ImageView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetails_inc);
        mContext = this;

        initialization();
        init();
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1(), "Preparation");
        adapter.addFragment(new Tab2(), "Details");
        adapter.addFragment(new Tab3(), "Videos");
        adapter.addFragment(new Tab4(), "Reviews");
        viewPager.setAdapter(adapter);
    }

    private void initialization() {
        sp_places = (Spinner) findViewById(R.id.sp_places);
        sp_places.setOnItemSelectedListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabTextColors(getResources().getColor(R.color.backgroundBlue), getResources().getColor(R.color.colorAccent));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager1);
        setupViewPager(viewPager);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txt_name = (TextView) headerView.findViewById(R.id.tv_notif_tips_title);
        ImageView ivNavDrawer = (ImageView) findViewById(R.id.iv_product_photo);
        TextView tvName = (TextView) findViewById(R.id.tv_notif_tips_title);

        tv_cartcount= (CircularTextView) findViewById(R.id.tv_cartcount);
        tv_cartcount.setSolidColor("#002f49");
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent e) {
                // How far the user has to scroll before it locks the parent vertical scrolling.
                final int margin = 10;
                final int fragmentOffset = v.getScrollX() % v.getWidth();

                if (fragmentOffset > margin && fragmentOffset < v.getWidth() - margin) {
                    viewPager.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        btn_menu = (ImageView) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

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

    private void init() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        for (int j = 0; j < SLIDER_TITLE.length; j++)
            TitleArray.add(SLIDER_TITLE[j]);


        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingAdapter_details(ActivityPDetails.this, ImagesArray));
        NUM_PAGES = IMAGES.length;
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
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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


