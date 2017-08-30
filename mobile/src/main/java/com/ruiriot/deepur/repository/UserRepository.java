package com.ruiriot.deepur.repository;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ruiriot.deepur.model.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiriot on 30/08/17.
 */

public class UserRepository {

    private final DatabaseReference reference;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageRef;

    public UserRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("users/");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://deepur-ce6d9.appspot.com/");
    }

    public String getUidCurrentUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUid();
        } else {
            return "";
        }
    }

    public void saveUser (final User user, Bitmap userImage, final OnFinished onFinished) {
        if (userImage != null)
            saveUserImageOnStorage(userImage, user.getUid(), new OnImageUpload() {
                @Override
                public void onUploadSuccess(Uri imageUrl) {
                    user.setPhotoUrl(imageUrl.toString());
                    saveUser(user, onFinished);
                }

                @Override
                public void onUploadFailure(String error) {
                    onFinished.onUserSaveFailed(error);
                }
            });
        else
            saveUser(user, onFinished);
    }

    private void saveUser(final User user, final OnFinished onFinished) {

        reference.child(getUidCurrentUser()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("saveOnUserList", "Completed");
                    //callback success
                    onFinished.onUserSaveSuccess();

                } else {
                    Log.d("saveOnGroupList", "Failure");
                    onFinished.onUserSaveFailed(task.getException().getMessage());
                }

            }
        });
    }

    private void saveUserImageOnStorage(Bitmap userImage, String userId, final OnImageUpload onImageUpload) {

        // Create a reference to 'images/id.jpg'
        StorageReference profileImageRef = storageRef.child("images/" + userId + ".jpg");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = profileImageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                onImageUpload.onUploadFailure(exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                onImageUpload.onUploadSuccess(downloadUrl);
            }
        });
    }

    interface OnImageUpload {
        void onUploadSuccess(Uri imageUrl);

        void onUploadFailure(String error);
    }

    public interface OnFinished {
        void onUserSaveSuccess();

        void onUserSaveFailed(String exception);
    }

    public void getNotFriendsUsersList(final OnGetNotFriendsList onGetNotFriendsList) {
        PeopleRepository peopleRepository = new PeopleRepository();
        peopleRepository.getFriendsIdFromUser(getUidCurrentUser(), new PeopleRepository.OnRequestFriendsId() {
            @Override
            public void onRequestFriendsIdSuccess(final List<String> contactsIds) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<User> users = new ArrayList<>();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            // If contact don't exists on friends list
                            User temp = dsp.getValue(User.class);
                            temp.setUid(dsp.getKey());
                            if (!contactsIds.contains(dsp.getKey())) {
                                users.add(temp);
                            }
                        }
                        onGetNotFriendsList.onGetNotFriendsListSuccess(users);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("getNotFriendsUsersList", databaseError.getMessage());
                        onGetNotFriendsList.onGetNotFriendsListError(databaseError.getMessage());
                    }
                });
            }
        });
    }

    public interface OnGetNotFriendsList {
        void onGetNotFriendsListSuccess(List<User> users);

        void onGetNotFriendsListError(String error);
    }
}
