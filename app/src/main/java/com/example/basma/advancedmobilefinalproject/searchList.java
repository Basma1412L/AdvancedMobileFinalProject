package com.example.basma.advancedmobilefinalproject;

import android.content.Intent;
import android.widget.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class searchList extends ArrayAdapter {



    private Activity context;
    private List<searchItem> searchList;


    public searchList(Activity context1, List<searchItem> searchList) {
        super(context1, R.layout.book_item_search,searchList);
        this.context = context1;
        this.searchList = searchList;
    }






    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  listViewItem = inflater.inflate(R.layout.book_item_search,null,true);

        final   TextView txtName=(TextView)listViewItem.findViewById(R.id.bookName);
        final  TextView txtStatus=(TextView)listViewItem.findViewById(R.id.bookStatus);
        Button viewProfile = (Button)listViewItem.findViewById(R.id.viewprofile);
        final searchItem searchItem =searchList.get(position);

        txtName.setText(searchItem.getUser().getName());
        txtStatus.setText(searchItem.getSearchBookStatus());
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,FriendProfileActivity.class);
                String UserId = searchItem.getUser().getId();
                intent.putExtra("id",UserId);
                context.startActivity(intent);


            }
        });

        return listViewItem;
    }





}
