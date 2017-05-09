package com.ruiriot.deepur.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
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
