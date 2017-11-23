package com.ruiriot.deepur.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.R;


/**
 * Created by ruiri on 08-May-17.
 */

public abstract class ActivityUtils {

    static public void callActivity(Context context, Class<?> clazz){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null){
            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
        }else {

            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
        }

    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        final String PREFERENCES_FILE = "materialsample_settings";
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        final String PREFERENCES_FILE = "materialsample_settings";
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    static public void requestPermission(Activity context, int requestCode, String... permissions){

        for (String p : permissions ) {

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                int result = ContextCompat.checkSelfPermission(context.getApplicationContext(), p);
            }

            if (requestCode != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, p)) {

                    Log.i("", "permission granted");
                } else {

                    ActivityCompat.requestPermissions(context, permissions, requestCode);

                }
            }
        }
    }

    static public void showProgressDialog(Context context) {

        ProgressDialog mProgressDialog;

        mProgressDialog = new ProgressDialog(context);
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog.setMessage(context.getString(R.string.login_loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    static public void hideProgressDialog(Context context) {

        ProgressDialog mProgressDialog;
        mProgressDialog = new ProgressDialog(context);
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
