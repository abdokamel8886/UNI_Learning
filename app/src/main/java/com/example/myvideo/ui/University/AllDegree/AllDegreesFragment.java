package com.example.myvideo.ui.University.AllDegree;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.DegreesRecyclerAdapter;
import com.example.myvideo.databinding.FragmentAllDegreesBinding;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.ui.University.Department.DepartmentsFragment;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AllDegreesFragment extends Fragment {

    FragmentAllDegreesBinding binding;
    AllDegreesViewModel viewModel;
    DegreesRecyclerAdapter adapter = new DegreesRecyclerAdapter();
    BottomNavigationView nav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_all_degrees, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentAllDegreesBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        viewModel = new ViewModelProvider(this).get(AllDegreesViewModel.class);
        getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.getinfo();
        viewModel.Grades.observe(getViewLifecycleOwner(), new Observer<ArrayList<UniversityModel.Grades>>() {
            @Override
            public void onChanged(ArrayList<UniversityModel.Grades> grades) {

                adapter.setList(grades);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new DegreesRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(UniversityModel.Grades grade) {
                        SharedModel.setSelected_Grade(grade);
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.home_frame , new DepartmentsFragment(),"bc").addToBackStack("bc").commit();
                    }
                });

            }
        });

    }
}