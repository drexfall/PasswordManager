package com.example.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanager.ui.dashboard.DashboardFragment;

import java.util.List;

public class PasswordAdapter extends BaseAdapter {
    private Context context;
    private List<Password> passwords;

    public PasswordAdapter(Context context, List<Password> passwords) {
        this.context = context;
        this.passwords = passwords;
    }

    public void setData(List<Password> passwords) {
        this.passwords = passwords;
    }

    @Override
    public int getCount() {
        return passwords.size();
    }

    @Override
    public Object getItem(int position) {
        return passwords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return passwords.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.password_list_item, parent, false);
        }

        TextView usernameTextView = convertView.findViewById(R.id.username_text_view);
        TextView passwordTextView = convertView.findViewById(R.id.password_text_view);
        TextView websiteTextView = convertView.findViewById(R.id.website_text_view);
        TextView typeTextView = convertView.findViewById(R.id.type_text_view);

        Password password = passwords.get(position);

        usernameTextView.setText(password.getUsername());
        passwordTextView.setText(password.getPassword());
        websiteTextView.setText(password.getWebsite());
        typeTextView.setText(password.getType());


        return convertView;
    }
}
