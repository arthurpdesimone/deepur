package com.ruiriot.deepur.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;

/**
 * Created by ruiri on 16-May-17.
 */

public abstract class ImageUtils {

    static public Palette.Swatch extractColor(Context context, int res) {

        Bitmap myBitmap = BitmapFactory.decodeResource(context.getResources(), res);
        Palette palette = new Palette.Builder(myBitmap).generate();

        return myBitmap == null ? (null):(palette.getLightVibrantSwatch());
    }
}
