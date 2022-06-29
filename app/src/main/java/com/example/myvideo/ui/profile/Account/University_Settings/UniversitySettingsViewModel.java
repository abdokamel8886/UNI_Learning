package com.example.myvideo.ui.profile.Account.University_Settings;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.UniversityModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UniversitySettingsViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<UniversityModel>>  List = new MutableLiveData<>();
    ArrayList<UniversityModel> Universities = new ArrayList<>();



    public void getData(){
        Universities.clear();
        ref.child("Universities").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Universities.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Universities.add(snapshot1.getValue(UniversityModel.class));

                }
                List.setValue(Universities);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
