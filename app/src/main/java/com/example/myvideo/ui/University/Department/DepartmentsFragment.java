package com.example.myvideo.ui.University.Department;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.DegreesRecyclerAdapter;
import com.example.myvideo.adapters.DepartmentsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentDepartmentsBinding;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.ui.University.TermsFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class DepartmentsFragment extends Fragment {

    FragmentDepartmentsBinding binding;
    DepartmentsRecyclerAdapter adapter = new DepartmentsRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_departments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDepartmentsBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void show(){
        adapter.setList((ArrayList<UniversityModel.Grades.Departments>) SharedModel.getSelected_Grade().getDepartments());
        binding.recycler.setAdapter(adapter);

        adapter.setOnItemClick(new DepartmentsRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(UniversityModel.Grades.Departments department) {
                SharedModel.setSelected_department(department);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_frame , new TermsFragment(),"bc").addToBackStack("bc").commit();
            }
        });


    }
}