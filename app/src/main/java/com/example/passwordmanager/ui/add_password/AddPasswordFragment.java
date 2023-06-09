package com.example.passwordmanager.ui.add_password;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.passwordmanager.Password;
import com.example.passwordmanager.PasswordContract;
import com.example.passwordmanager.PasswordDatabase;
import com.example.passwordmanager.databinding.FragmentAddPasswordBinding;
import com.example.passwordmanager.Websites;
import com.example.passwordmanager.ui.dashboard.DashboardFragment;

public class AddPasswordFragment extends Fragment {

    private FragmentAddPasswordBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAddPasswordBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        PasswordDatabase database = new PasswordDatabase(getContext().getApplicationContext());
        SQLiteDatabase mdb = database.getWritableDatabase();
        ArrayAdapter aa = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, Websites.websites);
        binding.spinner.setAdapter(aa);
        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b){
                    binding.addPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    binding.addPassword.setInputType(
                            InputType.TYPE_CLASS_TEXT|
                                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    );;
                }
            }
        });
        binding.addButton.setOnClickListener(view -> {
            String username = binding.addUsername.getText().toString();
            String password = binding.addPassword.getText().toString();
            if(username.isEmpty() || password.isEmpty()){

                Toast.makeText(getContext(), "Enter all fields!", Toast.LENGTH_SHORT).show();
                return;
            }
            String website = (String) binding.spinner.getSelectedItem();
            RadioButton rb = root.findViewById(binding.radiogroup.getCheckedRadioButtonId());
            String type = rb.getText().toString();

            database.addPassword(username,password,website,type);
            Toast.makeText(getContext().getApplicationContext(), "Password added!", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}