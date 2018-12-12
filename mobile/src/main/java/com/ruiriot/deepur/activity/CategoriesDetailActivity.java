package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruiriot.deepur.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by ruiri on 03-Dec-17.
 */

public class CategoriesDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");

        /*Uri uriCategoryImage = Uri.parse();
        InputStream image_stream = null;
        try {
            image_stream = context.getContentResolver().openInputStream(uriCategoryImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap= BitmapFactory.decodeStream(image_stream);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                holder.categoryName.setTextColor(p.getLightMutedSwatch().getTitleTextColor());
            }
        });*/

    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if (i == R.id.activity_categories_detail_back){
            finish();
        }
    }
}
