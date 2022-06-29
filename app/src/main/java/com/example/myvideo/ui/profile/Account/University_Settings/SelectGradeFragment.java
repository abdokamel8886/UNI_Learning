package com.example.myvideo.ui.profile.Account.University_Settings;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentSelectGradeBinding;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SelectGradeFragment extends Fragment {

    BottomNavigationView nav;
    FragmentSelectGradeBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_grade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSelectGradeBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.toolbar.setTitle(SharedModel.getSelected_university().getName());
        binding.address.setText(SharedModel.getSelected_university().getAddress());
        binding.about.setText(SharedModel.getSelected_university().getIntro());
        binding.departments.setText(SharedModel.getSelected_university().getDepartments());

        Glide.with(getContext())
                .load(SharedModel.getSelected_university().getImage())
                .into(binding.img);


        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }

    private void showDialog() {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}