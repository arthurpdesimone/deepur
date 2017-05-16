package com.ruiriot.deepur.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.BaseActivity;
import com.ruiriot.deepur.R;

import java.util.LinkedList;
import java.util.List;

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

    static public void requestPermission(BaseActivity context, int requestCode, String... permissions){

        for (String p : permissions ) {

            int result = ContextCompat.checkSelfPermission(context.getApplicationContext(), p);

            if (result != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, p)) {

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

    static public String getUserName(Context context) {
        AccountManager manager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        Account[] list = manager.getAccounts();

        for (Account account : list) {
            if (account.type.equals("com.google")) {
                Log.d(TAG, "account = " + account.name);
            }
        }
        return null;

    }

    static boolean isPermissionGranted(String[] permission, int[]requestCode){

        return false;
    }

    static public void circleImage (Context context, ImageView imageView){

        /*RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), drawable);
        circularBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(circularBitmapDrawable);*/

    }

    /* public Bitmap getBitmap (Context context, int res){

        Drawable myDrawable = context.getResources().getDrawable(res);
        Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        return myLogo;

    } */
}
