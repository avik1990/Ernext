package com.ernext.helperclass;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.Toast;
import com.ernext.ActivityMyprofile;
import com.ernext.R;

/**
 * Created by Avik on 29-01-2017.
 */

public class Eutils {

    public static ConnectionDetector cd;

    public static void openNavDrawer(int id, final Context mContext) {
        cd = new ConnectionDetector(mContext);

        if (id == R.id.nav_myprofile) {
            if (cd.isConnected()) {
                if (!(mContext instanceof ActivityMyprofile)) {
                    Intent profileintent = new Intent(mContext, ActivityMyprofile.class);
                    mContext.startActivity(profileintent);
                    ((Activity) mContext).overridePendingTransition(R.anim.anim_in_reverse, R.anim.anim_out_reverse);
                }
            } else {
                showToastShort(mContext, "No Internet");
            }
        }

        if (id == R.id.nav_logout) {
            showLogoutAlert(mContext, "Are you sure?", "Logout");
        }
    }

    public static void showToastLong(Context mContext, String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToastShort(Context mContext, String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLogoutAlert(final Context context, String msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* Intent profileintent = new Intent(context, ActivityLogin.class);
                context.startActivity(profileintent);
                ((Activity) context).overridePendingTransition(R.anim.anim_in_reverse, R.anim.anim_out_reverse);
                ((Activity) context).finish();

                cleardata(context);*/

            }
        });//second parameter used for onclicklistener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public static String getdeviceid(Context mContext)
    {
        String device_id="";
        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager.getDeviceId()!=null){
            device_id=telephonyManager.getDeviceId();
        }else {
            device_id = Settings.Secure.getString(mContext.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }
        return device_id;
    }

    public static String getGenericPreferences(Context mContext)
    {
        SharedPreferences preferences = mContext.getSharedPreferences("UrDocUserPreference", 0); // 0 - for private mode
        String flag = preferences.getString("msg", "");
        return flag;
    }

    public static void setGenericPreferences(Context mContext, String zone)
    {
        SharedPreferences preferences = mContext.getSharedPreferences("UrDocUserPreference", 0); // 0 - for private mode
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("msg", zone);
        editor.apply();
    }
}
