package com.example.myvideo.ui.profile.Account.FacultyInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentFacultyInfoBinding;
import com.example.myvideo.models.UniversityModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FacultyInfoFragment extends Fragment {


    FragmentFacultyInfoBinding binding;
    FacultyInfoViewModel viewModel;
    BottomNavigationView nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_faculty_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFacultyInfoBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        binding.bar.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(FacultyInfoViewModel.class);

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.getData();
        viewModel.model.observe(getViewLifecycleOwner(), new Observer<UniversityModel>() {
            @Override
            public void onChanged(UniversityModel universityModel) {

                binding.txt.setVisibility(View.VISIBLE);
                binding.txt1.setVisibility(View.VISIBLE);
                binding.txt2.setVisibility(View.VISIBLE);

                binding.toolbar.setTitle(universityModel.getName());
                binding.address.setText(universityModel.getAddress());
                binding.about.setText(universityModel.getIntro());
                binding.departments.setText(universityModel.getDepartments());
                Glide.with(getContext())
                        .load(universityModel.getImage())
                        .into(binding.img);
                binding.bar.setVisibility(View.GONE);
            }
        });
    }
}