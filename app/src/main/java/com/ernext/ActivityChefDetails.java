package com.ernext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.percent.PercentFrameLayout;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ernext.adapter.DishesAdapter;
import com.ernext.adapter.FeaturedChefAdapter;
import com.ernext.adapter.SlidingAdapter_dash;
import com.ernext.classtype.FeaturedChefs;
import com.ernext.helperclass.CircularTextView;
import com.ernext.helperclass.Eutils;

import java.util.ArrayList;
import java.util.List;

public class ActivityChefDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    Spinner sp_places;
    PercentRelativeLayout v_searchview;
    RelativeLayout rl_btn1, rl_btn2, rl_btn3, rl_btn4;
    private List<FeaturedChefs> list_featured_chefs = new ArrayList<>();
    View view1, view2, view3, view4;
    TextView tv_restauranat, tv_videos, tv_dishes, tv_about;
    CircularTextView tv_cartcount;
    NavigationView navigationView;
    ImageView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefs_details_nc);
        mContext = this;

        initialization();
    }

    private void initialization() {
        v_searchview = (PercentRelativeLayout) findViewById(R.id.v_searchview);
        v_searchview.setVisibility(View.GONE);

        rl_btn1 = (RelativeLayout) findViewById(R.id.rl_btn1);
        rl_btn2 = (RelativeLayout) findViewById(R.id.rl_btn2);
        rl_btn3 = (RelativeLayout) findViewById(R.id.rl_btn3);
        rl_btn4 = (RelativeLayout) findViewById(R.id.rl_btn4);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txt_name = (TextView) headerView.findViewById(R.id.tv_notif_tips_title);
        ImageView ivNavDrawer = (ImageView) findViewById(R.id.iv_product_photo);
        TextView tvName = (TextView) findViewById(R.id.tv_notif_tips_title);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        btn_menu = (ImageView) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

        tv_restauranat = (TextView) findViewById(R.id.tv_restauranat);
        tv_videos = (TextView) findViewById(R.id.tv_videos);
        tv_dishes = (TextView) findViewById(R.id.tv_dishes);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_cartcount = (CircularTextView) findViewById(R.id.tv_cartcount);
        tv_cartcount.setSolidColor("#002f49");

        rl_btn1.setOnClickListener(this);
        rl_btn2.setOnClickListener(this);
        rl_btn3.setOnClickListener(this);
        rl_btn4.setOnClickListener(this);


        sp_places = (Spinner) findViewById(R.id.sp_places);
        sp_places.setOnItemSelectedListener(this);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup) findViewById(R.id.v_addview);
        parent.removeAllViews();
        inflater.inflate(R.layout.f_layout1, parent);
        feedspinner();
        //createList();

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
        list_featured_chefs.clear();
        list_featured_chefs.add(new FeaturedChefs("1", "Avik Sutar", "3.2", "120", "https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/5/005/03f/2db/07e99f3.jpg"));
        list_featured_chefs.add(new FeaturedChefs("2", "Prosenjit Paul", "3.2", "123", "http://www.celebplasticsurgeryonline.com/wp-content/uploads/2016/02/Brad-Pitt-plastic-surgery-featured.jpg"));
        list_featured_chefs.add(new FeaturedChefs("3", "Debarun Biswas", "5", "126", "http://www.contactmusic.com/images/feature-images/johnny-depp-2016-alice-uk-premiere-01-670-500.jpg"));
        list_featured_chefs.add(new FeaturedChefs("4", "Asadhul Haq", "4", "127", "http://www.oncologica.com/wp-content/uploads/2016/06/98892061_UNHCR-Josie-world-large_transZgEkZX3M936N5BQK4Va8RQJ6Ra64K3tAxfZq0dvIBJw.jpg"));
        list_featured_chefs.add(new FeaturedChefs("5", "Bilanjo Mukhrjee", "3", "345", "http://www.ansa.it/webimages/foto_large_ls_350/2016/1/13/fd13e7f6ca8fd863f91a2936cdd7f8d4.jpg"));
        list_featured_chefs.add(new FeaturedChefs("6", "Josh Doe", "3", "224", "http://www.fansofcinema.com/wp-content/uploads/2015/09/All-Upcoming-Films-of-Aamir-Khan-in-2015-2016-2017.jpg"));
        list_featured_chefs.add(new FeaturedChefs("7", "Sanjay Chowdhury", "2", "138", "http://www.stylerug.net/wp-content/uploads/2016/12/King-Khan.jpg"));
        list_featured_chefs.add(new FeaturedChefs("8", "Manish Madhukar", "4", "112", "https://cdn.techinasia.com/wp-content/uploads/2015/07/jackma.jpg"));
        list_featured_chefs.add(new FeaturedChefs("9", "Ankeekita", "5", "145", "http://image6.buzzintown.com/files/event/upload_82000/upload_original/414725-cooking-with-sanjeev-kapoor.jpg"));
        list_featured_chefs.add(new FeaturedChefs("10", "Anikita", "3", "167", "https://s3.amazonaws.com/b.assets.cmshanken.mshanken.com/foodarts/article/19497/THUMBNAIL/bemedaled-and-bemused-best-chef-and-best-guy-jos-andrs-is-a-winner-in-anyones-book.png"));
        list_featured_chefs.add(new FeaturedChefs("11", "Sneha Singh", "4", "190", "http://asian-fusion.com/wp-content/uploads/2015/10/Srijith-Gopinathan.jpg"));
        list_featured_chefs.add(new FeaturedChefs("12", "Manish Singh", "3", "234", "http://starsunfolded.1ygkv60km.netdna-cdn.com/wp-content/uploads/2013/08/Ajay-Devgan.jpg"));
        list_featured_chefs.add(new FeaturedChefs("13", "Rakesh Samanta", "3", "108", "http://starsunfolded.1ygkv60km.netdna-cdn.com/wp-content/uploads/2013/08/Salman-Khan.jpg"));
        list_featured_chefs.add(new FeaturedChefs("14", "Saikat Basu", "2", "190", "https://s-media-cache-ak0.pinimg.com/originals/bd/0e/b3/bd0eb380108a59dd5d5d4415da43c620.jpg"));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (v == rl_btn1) {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.INVISIBLE);

            tv_about.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_dishes.setTextColor(getResources().getColor(R.color.black));
            tv_videos.setTextColor(getResources().getColor(R.color.black));
            tv_restauranat.setTextColor(getResources().getColor(R.color.black));

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup parent = (ViewGroup) findViewById(R.id.v_addview);
            parent.removeAllViews();
            inflater.inflate(R.layout.f_layout1, parent);
        }
        if (v == rl_btn2) {
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.INVISIBLE);

            tv_restauranat.setTextColor(getResources().getColor(R.color.black));
            tv_dishes.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_videos.setTextColor(getResources().getColor(R.color.black));
            tv_about.setTextColor(getResources().getColor(R.color.black));

            createList();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup parent = (ViewGroup) findViewById(R.id.v_addview);
            parent.removeAllViews();
            inflater.inflate(R.layout.f_layout2, parent);

            RecyclerView fc_recycler_view = (RecyclerView) parent.findViewById(R.id.fc_recycler_view);
            fc_recycler_view.setLayoutManager(new GridLayoutManager(ActivityChefDetails.this, 2));
            if (list_featured_chefs.size() > 0) {
                DishesAdapter ca = new DishesAdapter(mContext, list_featured_chefs);
                fc_recycler_view.setAdapter(ca);
            }
        }

        if (v == rl_btn3) {
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.VISIBLE);
            view4.setVisibility(View.INVISIBLE);

            tv_about.setTextColor(getResources().getColor(R.color.black));
            tv_dishes.setTextColor(getResources().getColor(R.color.black));
            tv_videos.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_restauranat.setTextColor(getResources().getColor(R.color.black));

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup parent = (ViewGroup) findViewById(R.id.v_addview);
            parent.removeAllViews();
            inflater.inflate(R.layout.f_layout1, parent);
        }
        if (v == rl_btn4) {
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.VISIBLE);

            tv_about.setTextColor(getResources().getColor(R.color.black));
            tv_dishes.setTextColor(getResources().getColor(R.color.black));
            tv_videos.setTextColor(getResources().getColor(R.color.black));
            tv_restauranat.setTextColor(getResources().getColor(R.color.colorAccent));

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup parent = (ViewGroup) findViewById(R.id.v_addview);
            parent.removeAllViews();
            inflater.inflate(R.layout.f_layout1, parent);
        }

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
