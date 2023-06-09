package com.example.passwordmanager.ui.dashboard;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passwordmanager.Password;
import com.example.passwordmanager.PasswordAdapter;
import com.example.passwordmanager.PasswordContract;
import com.example.passwordmanager.PasswordDatabase;
import com.example.passwordmanager.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardFragment extends Fragment {
    private PasswordAdapter mAdapter;
    private ArrayList<Password> mPasswords = new ArrayList<>();

    private SQLiteDatabase mDatabase;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        PasswordDatabase dbHelper = new PasswordDatabase(getContext().getApplicationContext());
        mDatabase = dbHelper.getWritableDatabase();
        mPasswords = dbHelper.getPasswords();
        mAdapter = new PasswordAdapter(binding.getRoot().getContext(), mPasswords);
        ;
        binding.passwordListView.setAdapter(mAdapter);




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}