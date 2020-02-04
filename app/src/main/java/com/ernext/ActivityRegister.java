package com.ernext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.opengl.ETC1Util;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ernext.helperclass.ConnectionDetector;
import com.ernext.helperclass.Eutils;
import com.ernext.helperclass.NukeSSLCerts;
import com.ernext.helperclass.VolleySingleton;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import hugo.weaving.DebugLog;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    Button btn_register;
    EditText et_name, et_email, et_isd, et_number, et_password;
    CheckBox chk_box;
    Context mContext;
    ProgressDialog pDialog;
    String st_name, st_email, st_isd, st_phone, st_password, st_chk;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        cd = new ConnectionDetector(mContext);
        //new NukeSSLCerts();
        initialization();

    }

    private void initialization() {
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_isd = (EditText) findViewById(R.id.et_isd);
        et_number = (EditText) findViewById(R.id.et_number);
        et_password = (EditText) findViewById(R.id.et_password);
        chk_box = (CheckBox) findViewById(R.id.chk_box);

        HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        DefaultHttpClient client = new DefaultHttpClient();
        SchemeRegistry registry = new SchemeRegistry();
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
        registry.register(new Scheme("https", socketFactory, 443));
        SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
        DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
        final String url = "https://encrypted.google.com/";

    }

    @Override
    public void onClick(View v) {
        if (v == btn_register) {
            /*Intent i = new Intent(getBaseContext(), Dashboard.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
            st_name = et_name.getText().toString().trim();
            st_email = et_email.getText().toString().trim();
            st_isd = et_isd.getText().toString().trim();
            st_phone = et_number.getText().toString().trim();
            st_password = et_password.getText().toString().trim();
            chk_box.setChecked(true);

            if (!st_name.isEmpty()) {
                if (!st_email.isEmpty()) {
                    if (!st_isd.isEmpty()) {
                        if (!st_phone.isEmpty()) {
                            if (!st_password.isEmpty()) {
                                if (chk_box.isChecked()) {
                                    if (cd.isConnected()) {
                                        makelogin();
                                    } else {
                                        Eutils.showToastShort(mContext, "Please Check your Internet Connection");
                                    }
                                } else {
                                   /* chk_box.setChecked(true);*/
                                    Eutils.showToastShort(mContext, "Please Agree with our terms and conditions");
                                }
                            } else {
                                et_password.requestFocus();
                                Eutils.showToastShort(mContext, "Please enter password");
                            }

                        } else {
                            et_number.requestFocus();
                            Eutils.showToastShort(mContext, "Please enter phone no.");
                        }
                    } else {
                        et_isd.requestFocus();
                        Eutils.showToastShort(mContext, "Please enter Country code");
                    }
                } else {
                    et_email.requestFocus();
                    Eutils.showToastShort(mContext, "Please enter email");
                }

            } else {
                et_name.requestFocus();
                Eutils.showToastShort(mContext, "Please enter name");
            }
        }
    }

    private void makelogin() {
        String url_register = getResources().getString(R.string.baseurl)
                + getResources().getString(R.string.register)
                + getResources().getString(R.string.format);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_register,
                new Response.Listener<String>() {
                    String status = "", msg = "", member_id = "", mobile = "", name = "", password = "", otp = "", email = "", mobile_isd = "", user_status = "", user_type = "", doc = "", dom = "", user_gender = "", user_dob = "", user_location = "", user_zip = "", user_address = "", user_allergies = "", user_avatar = "", imgurl = "";

                    @DebugLog
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try {
                            JSONObject jObj = new JSONObject(response);

                            status = jObj.getString("status");
                            msg = jObj.getString("msg");
                          //  Eutils.setGenericPreferences(mContext, msg);

                            if (status.equals("1") || status.equals("1")) {
                                /*imgurl = jObj.getString("imgurl");
                                name = jObj.getJSONObject("data").getString("user_name");
                                mobile = jObj.getJSONObject("data").getString("user_mobile");
                                mobile_isd = jObj.getJSONObject("data").getString("user_mobile_isd");
                                email = jObj.getJSONObject("data").getString("user_email");
                                password = jObj.getJSONObject("data").getString("user_password");
                                otp = jObj.getJSONObject("data").getString("user_otp");
                                user_status = jObj.getJSONObject("data").getString("user_status");
                                user_type = jObj.getJSONObject("data").getString("user_type");
                                member_id = jObj.getJSONObject("data").getString("user_id");
                                doc = jObj.getJSONObject("data").getString("DOC");
                                dom = jObj.getJSONObject("data").getString("DOM");
                                user_gender = jObj.getJSONObject("data").getString("user_gender");
                                user_dob = jObj.getJSONObject("data").getString("user_dob");
                                user_allergies = jObj.getJSONObject("data").getString("user_allergies");
                                user_avatar = jObj.getJSONObject("data").getString("user_avatar");*/
                              //  goToHomeActivity();
                            }

                            /*if (status.equals("4") || status.equals("1")) {
                                *//*UrDocUserUtils.setMemberStatusMessagePreferences(mContext, msg.trim());
                                UrDocUserUtils.set_user_IdPreference(mContext, member_id.trim());
                                UrDocUserUtils.setMemberStatusPreferences(mContext, Integer.valueOf(status));
                                UrDocUserUtils.setUser_emailPreferences(mContext, email);
                                UrDocUserUtils.setUser_namePreferences(mContext, name);
                                UrDocUserUtils.setUser_passwordPreferences(mContext, password);
                                UrDocUserUtils.setUser_mobilePreferences(mContext, mobile);
                                UrDocUserUtils.setUser_otpPreferences(mContext, otp);

                                UrDocUserUtils.setUser_mobileisdPreferences(mContext, mobile_isd);
                                UrDocUserUtils.setUser_statusPreferences(mContext, user_status);
                                UrDocUserUtils.setUser_typePreferences(mContext, user_type);
                                UrDocUserUtils.setUser_docPreferences(mContext, doc);
                                UrDocUserUtils.setUser_domPreferences(mContext, dom);
                                UrDocUserUtils.setUser_genderPreferences(mContext, user_gender);
                                UrDocUserUtils.setUser_dobPreferences(mContext, user_dob);
                                UrDocUserUtils.setUser_loactionPreferences(mContext, user_location);
                                UrDocUserUtils.setUser_zipPreferences(mContext, user_zip);
                                UrDocUserUtils.setUser_addressPreferences(mContext, user_address);
                                UrDocUserUtils.setUser_allergiesPreferences(mContext, user_allergies);
                                UrDocUserUtils.setUser_profImagePreferences(mContext, user_avatar);
                                UrDocUserUtils.setUser_profImageUrlPreferences(mContext, imgurl);

                                pDialog.dismiss();
                                UrDocUserUtils.setOtpPreferences(mContext, true);
                                UrDocUserUtils.setGCMPreferences(mContext, true, devID);*//*

                            }*/ else {
                                //Eutils.showToastShort(mContext, msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", st_phone);
                params.put("isd", st_isd);
                params.put("email", st_email);
                params.put("name", st_name);
                params.put("password", st_password);
                params.put("device_type", "A");
                params.put("device_token", "csdbhjsdfsd#&#ndsfsdfdsngbfdf");
                params.put("device_id", Eutils.getdeviceid(mContext));

                return params;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }

    /*private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    private void goToHomeActivity() {
        Intent i = new Intent(getBaseContext(), Dashboard.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
