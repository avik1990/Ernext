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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ernext.ActivityChefDetails;
import com.ernext.R;
import com.ernext.classtype.FeaturedChefs;
import com.ernext.classtype.Mycart;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Avik on 11-01-2017.
 */
public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.MyViewHolder> {

    private List<Mycart> mycartlist;
    Context mContext;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_dishimage;
        TextView tv_dishname, tv_subcategory, tv_checfname, tv_sprice;
        RatingBar rt_dish;
        ProgressBar progressbar;
        Spinner sp_qty;
        RelativeLayout rl_remove;

        public MyViewHolder(View view) {
            super(view);

            tv_dishname = (TextView) view.findViewById(R.id.tv_dishname);
            tv_subcategory = (TextView) view.findViewById(R.id.tv_subcategory);
            iv_dishimage = (ImageView) view.findViewById(R.id.iv_chefimage);
            rt_dish = (RatingBar) view.findViewById(R.id.rt_chef);
            sp_qty = (Spinner) view.findViewById(R.id.sp_qty);
            rl_remove = (RelativeLayout) view.findViewById(R.id.rl_remove);
            progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
            tv_checfname = (TextView) view.findViewById(R.id.tv_checfname);
            tv_sprice = (TextView) view.findViewById(R.id.tv_sprice);

        }
    }

    public MycartAdapter(Context mContext, List<Mycart> mycartlist) {
        this.mContext = mContext;
        this.mycartlist = mycartlist;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mycart c = mycartlist.get(position);
        holder.tv_dishname.setText(c.getP_name());
        holder.tv_checfname.setText("Chef "+c.getP_checfname());
        holder.tv_subcategory.setText(String.valueOf(c.getP_subcategory()));
        holder.rt_dish.setRating(Float.parseFloat(c.getP_rating()));
        holder.tv_sprice.setText(String.valueOf("Rs "+c.getP_price()));
        holder.progressbar.setVisibility(View.VISIBLE);
        holder.iv_dishimage.setVisibility(View.VISIBLE);
        final ProgressBar progressView = holder.progressbar;
        Picasso.with(mContext)
                .load(c.getP_image())
                .into(holder.iv_dishimage, new com.squareup.picasso.Callback() {
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
        return mycartlist.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listbar_cart, parent, false);
        return new MyViewHolder(v);
    }
}
