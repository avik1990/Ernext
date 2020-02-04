package com.ernext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ernext.adapter.FeaturedChefAdapter;
import com.ernext.adapter.SlidingAdapter_dash;
import com.ernext.classtype.FeaturedChefs;
import com.ernext.helperclass.CircularTextView;
import com.ernext.helperclass.Eutils;

import java.util.ArrayList;
import java.util.List;

public class ActivityChefs extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    RecyclerView fc_recycler_view;
    private List<FeaturedChefs> list_featured_chefs = new ArrayList<>();
    Context mContext;
    Spinner sp_places;
    CircularTextView tv_cartcount;
    NavigationView navigationView;
    ImageView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefs_inc);
        mContext = this;

        initialization();
    }

    private void initialization() {
        fc_recycler_view = (RecyclerView) findViewById(R.id.fc_recycler_view);
        /*fc_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));*/
        fc_recycler_view.setLayoutManager(new GridLayoutManager(ActivityChefs.this, 2));
        sp_places = (Spinner) findViewById(R.id.sp_places);
        sp_places.setOnItemSelectedListener(this);
        tv_cartcount= (CircularTextView) findViewById(R.id.tv_cartcount);
        tv_cartcount.setSolidColor("#002f49");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView txt_name = (TextView) headerView.findViewById(R.id.tv_notif_tips_title);
        ImageView ivNavDrawer = (ImageView) findViewById(R.id.iv_product_photo);
        TextView tvName = (TextView) findViewById(R.id.tv_notif_tips_title);

        btn_menu = (ImageView) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(this);

        feedspinner();
        createList();

        FeaturedChefAdapter ca = new FeaturedChefAdapter(mContext, list_featured_chefs);
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
