package com.ernext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Splash extends AppCompatActivity {

    Context mContext;
    private static final int SPLASH_TIME = 2 * 1000;
    boolean otp_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = this;

        //otp_flag = SharedUtils.getOtpPreferences(mContext);
        if (otp_flag) {
            goToHomeActivity();
        } else {
            goToLoginactivity();
        }

    }


    private void goToHomeActivity() {
        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2 * 1000);
                    Intent i = new Intent(getBaseContext(), Dashboard.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    private void goToLoginactivity() {
        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2 * 1000);
                    Intent i = new Intent(getBaseContext(), ActivityRegister.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

}
