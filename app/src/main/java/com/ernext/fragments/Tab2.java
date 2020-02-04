package com.ernext.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.ernext.R;

/**
 * Created by Belal on 2/3/2016.
 */

public class Tab2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab2, container, false);
        final ScrollView sv= (ScrollView) view.findViewById(R.id.v_scrollView);
        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sv.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        return view;
    }
}
