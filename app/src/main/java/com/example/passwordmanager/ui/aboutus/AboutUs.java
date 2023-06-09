package com.example.passwordmanager.ui.aboutus;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordmanager.R;
import com.example.passwordmanager.databinding.FragmentAboutUsBinding;
import com.example.passwordmanager.databinding.FragmentDashboardBinding;

public class AboutUs extends Fragment {
    private FragmentAboutUsBinding binding;

    public static AboutUs newInstance() {
        return new AboutUs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}