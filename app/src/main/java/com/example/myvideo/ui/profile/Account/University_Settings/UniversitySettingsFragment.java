package com.example.myvideo.ui.profile.Account.University_Settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myvideo.R;
import com.example.myvideo.adapters.UniRecyclerAdapter;
import com.example.myvideo.databinding.FragmentUniversitySettingsBinding;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class UniversitySettingsFragment extends Fragment {

    FragmentUniversitySettingsBinding binding;
    UniRecyclerAdapter adapter = new UniRecyclerAdapter();
    UniversitySettingsViewModel viewModel;

    BottomNavigationView nav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_university_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentUniversitySettingsBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(UniversitySettingsViewModel.class);
        binding.bar.setVisibility(View.VISIBLE);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.getData();

        viewModel.List.observe(getViewLifecycleOwner(), new Observer<ArrayList<UniversityModel>>() {
            @Override
            public void onChanged(ArrayList<UniversityModel> universityModels) {
                adapter.setList(universityModels);
                binding.recycler.setAdapter(adapter);

                binding.bar.setVisibility(View.GONE);

                binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });

                adapter.setOnItemClick(new UniRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(UniversityModel universityModel) {
                        SharedModel.setSelected_university(universityModel);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new SelectGradeFragment() , "sg")
                                .addToBackStack("sg").commit();
                    }
                });

            }
        });

    }

}