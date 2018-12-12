package com.ruiriot.deepur.repository;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.model.Category;
import com.ruiriot.deepur.repository.callback.OnDataFetchCallBack;
import com.ruiriot.deepur.repository.callback.OnDataSavedCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryRepository {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReference("categoriesV2");


    public void save(Category category, final OnDataSavedCallBack callBack){
        String key = reference.push().getKey();
        category.setId(key);
        reference.child(key).setValue(category)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            callBack.onSave();
                        } else {
                            callBack.onError(task.getException());
                        }
                    }
                });
    }

    public void getByID(String id, final OnDataFetchCallBack<Category> callBack){
        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        callBack.onFetch(dataSnapshot.getValue(Category.class));
                    } catch (Exception e){
                        e.printStackTrace();
                        callBack.onError(e);
                    }
                } else {
                    callBack.onFetch(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callBack.onError(databaseError.toException());
            }
        });
    }

    public void getAll(final OnDataFetchCallBack<List<Category>> callBack){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        GenericTypeIndicator<HashMap<String,Category>> typeIndicator
                                = new GenericTypeIndicator<HashMap<String,Category>>() {};
                        HashMap<String, Category> node;
                        node = dataSnapshot.getValue(typeIndicator);
                        List<Category> values = new ArrayList<>(node.values());
                        callBack.onFetch(values);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callBack.onError(e);
                    }
                } else {
                    callBack.onFetch(null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callBack.onError(databaseError.toException());
            }
        });
    }
}
