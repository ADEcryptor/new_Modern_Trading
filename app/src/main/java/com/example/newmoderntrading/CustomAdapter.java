package com.example.newmoderntrading;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String>{

    CustomAdapter(@NonNull Context context, ArrayList dataBaseAdapter) {
        super(context, R.layout.custom_items_list,dataBaseAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(getContext());
        View customView = li.inflate(R.layout.custom_items_list,parent,false);
        String singleItem = getItem(position);
        TextView tvItem = customView.findViewById(R.id.textItem);
        tvItem.setText(singleItem);

        return customView;
    }
}