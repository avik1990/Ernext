package com.ernext.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ernext.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SONU on 29/08/15.
 */
public class SlidingAdapter_dash extends PagerAdapter {


    private ArrayList<String> IMAGES;
    private ArrayList<String> TITLE;
    private LayoutInflater inflater;
    private Context context;
    ProgressBar progressbar;


    public SlidingAdapter_dash(Context context, ArrayList<String> IMAGES, ArrayList<String> TITLE) {
        this.context = context;
        this.IMAGES = IMAGES;
        this.TITLE = TITLE;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.slider_img_dash, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        final ProgressBar progressbar = (ProgressBar) imageLayout.findViewById(R.id.progressbar);

        //imageView.setImageResource(IMAGES.get(position));
        Log.d("Imagepath", IMAGES.get(position));
        /*Picasso.with(context)
                .load(IMAGES.get(position))
                //.placeholder(R.drawable.user_placeholder)
                //.error(R.drawable.user_placeholder_error)
                .into(imageView);*/


        progressbar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        final ProgressBar progressView = progressbar;
        Picasso.with(context)
                .load(IMAGES.get(position))
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        // TODO Auto-generated method stub

                    }
                });

    view.addView(imageLayout,0);


    return imageLayout;
}

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
