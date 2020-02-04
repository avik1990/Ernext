package com.ernext.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ernext.ActivityChefDetails;
import com.ernext.R;
import com.ernext.classtype.FeaturedChefs;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avik on 11-01-2017.
 */
public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.MyViewHolder> {

    private List<FeaturedChefs> countryList;
    Context mContext;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_chefimage;
        TextView tv_checfname;
        RatingBar rt_chef;
        ProgressBar progressbar;


        public MyViewHolder(View view) {
            super(view);
            iv_chefimage = (ImageView) view.findViewById(R.id.iv_chefimage);
            tv_checfname = (TextView) view.findViewById(R.id.tv_checfname);
            rt_chef = (RatingBar) view.findViewById(R.id.rt_chef);
            progressbar = (ProgressBar) view.findViewById(R.id.progressbar);

        }
    }

    public DishesAdapter(Context mContext, List<FeaturedChefs> countryList) {
        this.mContext = mContext;
        this.countryList = countryList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FeaturedChefs c = countryList.get(position);
        holder.tv_checfname.setText(c.getC_name());
        holder.rt_chef.setRating(Float.parseFloat(c.getC_rates()));
        holder.progressbar.setVisibility(View.VISIBLE);
        holder.iv_chefimage.setVisibility(View.VISIBLE);
        final ProgressBar progressView = holder.progressbar;
        Picasso.with(mContext)
                .load(c.getC_images())
                .into(holder.iv_chefimage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });



    }

    @Override
    public int getItemCount() {
        Log.d("RV", "Item size [" + countryList.size() + "]");
        return countryList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbar_dishes, parent, false);
        return new MyViewHolder(v);
    }
}
