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
import com.ruiriot.deepur.model.Topic;
import com.ruiriot.deepur.repository.callback.OnDataFetchCallBack;
import com.ruiriot.deepur.repository.callback.OnDataSavedCallBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicRepository {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference reference = database.getReference("topic");
    private final DatabaseReference categoryReference = database.getReference("categoriesV2");

    public void save(Topic topic, final OnDataSavedCallBack callBack) {
        String key = reference.push().getKey();
        topic.setId(key);
        reference.child(key).setValue(topic)
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

    public void getByID(String id, final OnDataFetchCallBack<Topic> callBack) {
        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        final Topic topic = dataSnapshot.getValue(Topic.class);

                        getCategoryName(topic.getCategoryID(), new OnDataFetchCallBack<String>() {
                            @Override
                            public void onFetch(String data) {
                                topic.setCategoryName(data);
                                callBack.onFetch(topic);
                            }

                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                                callBack.onError(e);
                            }
                        });
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

    public void getAll(final OnDataFetchCallBack<List<Topic>> callBack) {
        reference.addListenerForSingleValueEvent(getListenerToFetchAndMapToList(callBack));
    }

    public void getAllTrending(final OnDataFetchCallBack<List<Topic>> callBack) {
        reference.orderByChild("trending").equalTo(true)
                .addListenerForSingleValueEvent(getListenerToFetchAndMapToList(callBack));
    }

    public void getAllByCategory(String categoryID, final OnDataFetchCallBack<List<Topic>> callBack) {
        reference.orderByChild("categoryID").equalTo(categoryID)
                .addListenerForSingleValueEvent(getListenerToFetchAndMapToList(callBack));
    }

    @NonNull
    private ValueEventListener getListenerToFetchAndMapToList(final OnDataFetchCallBack<List<Topic>> callBack) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        GenericTypeIndicator<HashMap<String, Topic>> typeIndicator
                                = new GenericTypeIndicator<HashMap<String, Topic>>() {
                        };
                        HashMap<String, Topic> node;
                        node = dataSnapshot.getValue(typeIndicator);
                        final List<Topic> topics = new ArrayList<>(node.values());
                        final AtomicInteger atomicInteger = new AtomicInteger(topics.size());

                        for (final Topic topic : topics) {
                            getCategoryName(topic.getCategoryID(), new OnDataFetchCallBack<String>() {
                                @Override
                                public void onFetch(String data) {
                                    topic.setCategoryName(data);
                                    if (atomicInteger.decrementAndGet() == 0) {
                                        callBack.onFetch(topics);
                                    }
                                }

                                @Override
                                public void onError(Exception e) {
                                    e.printStackTrace();
                                    atomicInteger.decrementAndGet();
                                }
                            });
                        }
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
        };
    }

    private void getCategoryName(String categoryID, final OnDataFetchCallBack<String> callBack) {
        categoryReference.child(categoryID).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        callBack.onFetch(dataSnapshot.getValue(String.class));
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
