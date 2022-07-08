package com.example.myvideo.ui.post;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.CommentModel;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<CommentModel> commentModels = new ArrayList<>();
    MutableLiveData<ArrayList<CommentModel>> list = new MutableLiveData<>();

    MutableLiveData<String> success = new MutableLiveData<>();


    public void sendComment(String comment){
        ref.child("Comment").child(SharedModel.getSelected_post().getId())
                .push().setValue(new CommentModel(comment , SharedModel.getImage() , SharedModel.getUsername()));
    }

    public void getComments(){
        commentModels.clear();
        ref.child("Comment").child(SharedModel.getSelected_post().getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                commentModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    commentModels.add(snapshot1.getValue(CommentModel.class));


                }


                list.setValue(commentModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendWithout(PostModel model){
        String id = ref.push().getKey();
        model.setId(id);
        ref.child("Posts").child(id).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success.setValue("ddd");
            }
        });

    }




}
