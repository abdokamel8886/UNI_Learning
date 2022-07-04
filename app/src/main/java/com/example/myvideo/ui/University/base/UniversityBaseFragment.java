package com.example.myvideo.ui.University.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentUniversityBaseBinding;
import com.example.myvideo.ui.University.AllDegree.AllDegreesFragment;
import com.example.myvideo.ui.University.MyUniversity.MyUniversityFragment;
import com.example.myvideo.ui.myHome.MyBooks.MyBooksBaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class UniversityBaseFragment extends Fragment {

    FragmentUniversityBaseBinding binding;
    BottomNavigationView nav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_university_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentUniversityBaseBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){
        binding.myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyUniversityFragment(),"muu")
                        .addToBackStack("muu").commit();
            }
        });
        binding.AllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new AllDegreesFragment(),"au")
                        .addToBackStack("au").commit();
            }
        });
    }
}