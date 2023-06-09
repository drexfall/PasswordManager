package com.example.passwordmanager.ui.generator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passwordmanager.databinding.FragmentNotificationsBinding;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneratorFragment extends Fragment {
    static char[] SYMBOLS = "^$*.[]{}()?-\"!@#%&/\\,><':;|_~`".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS;
    static Random rand = new SecureRandom();
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.seekbarPasswordLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int length, boolean b) {
                binding.textviewPasswordLengthValue.setText(String.valueOf(length));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.buttonGeneratePassword.setOnClickListener(view -> {
            ALL_CHARS = new char[0];
            int length;
            try{
            length = Integer.parseInt((String) binding.textviewPasswordLengthValue.getText());}
            catch (Exception e){
                length = 8;
                binding.seekbarPasswordLength.setProgress(8);
            }
            if (length >= 0) {
                char[] password = new char[length];

                if(binding.lowerCheck.isChecked()){
                ALL_CHARS = concat(ALL_CHARS,LOWERCASE);
                }
                if(binding.upperCheck.isChecked()){
                    ALL_CHARS = concat(ALL_CHARS,UPPERCASE);
                }
                if(binding.digitCheck.isChecked()){
                    ALL_CHARS = concat(ALL_CHARS,NUMBERS);
                }
                if(binding.symbolCheck.isChecked()){
                    ALL_CHARS = concat(ALL_CHARS,SYMBOLS);
                }
                try {
                    for (int i = 1; i < length; i++) {
                        password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
                    }

                    List<String> letters = Arrays.asList(String.valueOf(password));
                    Collections.shuffle(letters);
                    String shuffled = "";
                    for (String letter : letters) {
                        shuffled += letter;
                    }


                    binding.textviewPassword.setText(shuffled);
                }catch (Exception e){
                    Toast.makeText(root.getContext(), "Select at least one option!", Toast.LENGTH_SHORT).show();
                    return;
                }


            }


        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public char[] concat(char[] arr1, char[] arr2){
        int arr1_length;
        int arr2_length;

        try{
            arr1_length = arr1.length;
        }catch (Exception e){
            arr1_length = 0;
        }
        try{
            arr2_length = arr2.length;
        }catch (Exception e){
            arr2_length = 0;
        }

        char[] charArray = new char[arr1_length+arr2_length];

        for (int i = 0; i < arr1_length; i++) {
            charArray[i] = arr1[i];
        }
        for (int i = 0; i < arr2_length; i++) {
            charArray[arr1_length+i] = arr2[i];
        }

        return charArray;
    }
}