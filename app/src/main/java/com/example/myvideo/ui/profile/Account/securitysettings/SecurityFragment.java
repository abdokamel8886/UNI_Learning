package com.example.myvideo.ui.profile.Account.securitysettings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentSecurityBinding;
import com.example.myvideo.utils.SharedModel;


public class SecurityFragment extends Fragment {

    FragmentSecurityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_security, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSecurityBinding.bind(view);

        binding.mailEdit.setText(SharedModel.getMail());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}