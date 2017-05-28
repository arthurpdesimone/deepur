package com.ruiriot.deepur.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.R;

import static android.content.Context.ACCOUNT_SERVICE;
import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by ruiri on 08-May-17.
 */

public abstract class ActivityUtils {

    static public void callActivity(Context context, Class<?> clazz){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail;

        if (user != null){
            userEmail = user.getEmail();
            Intent intent = new Intent(context, clazz);
            intent.putExtra(Intent.EXTRA_EMAIL, userEmail);
            context.startActivity(intent);
        }else {
            Toast.makeText(context, "User not found.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    static public void requestPermission(Activity context, int requestCode, String... permissions){

        for (String p : permissions ) {

            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                int result = ContextCompat.checkSelfPermission(context.getApplicationContext(), p);
            }

            if (requestCode != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, p)) {

                    Log.i(TAG, "permission granted");
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
