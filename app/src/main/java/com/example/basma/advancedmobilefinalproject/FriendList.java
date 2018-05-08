package com.example.basma.advancedmobilefinalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class FriendList extends ArrayAdapter {

    private Activity context;
    private List<Book> searchList;

    public FriendList(Activity context1, List<Book> searchList) {
        super(context1, R.layout.book_item_friend,searchList);
        this.context = context1;
        this.searchList = searchList;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  listViewItem = inflater.inflate(R.layout.book_item_friend,null,true);

        final TextView txtName=(TextView)listViewItem.findViewById(R.id.bookName2);
        final  TextView txtStatus=(TextView)listViewItem.findViewById(R.id.bookStatus2);
       // Button viewProfile = (Button)listViewItem.findViewById(R.id.viewprofile);
        Button exchange = (Button)listViewItem.findViewById(R.id.exchange2);
        final Book book =searchList.get(position);

        txtName.setText(book.getBook_Name());
        txtStatus.setText(book.getBook_Status());

   /*     changeStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater=context.getLayoutInflater();
                final View displayView = inflater.inflate(R.layout.add_book,null);
                dialogBuilder.setView(displayView);

                final EditText bookName = (EditText)displayView.findViewById(R.id.editTextName);
                final Spinner status = (Spinner)displayView.findViewById(R.id.statusSpinner);
                Button register=(Button)displayView.findViewById(R.id.doneBtn);

                dialogBuilder.setTitle("Updating book status");

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String n =bookName.getText().toString().trim();
                        String g =status.getSelectedItem().toString();


                        if(!TextUtils.isEmpty(n))
                        {

                            Book bookUpdated = new Book(n,g);
                            booksList.set(position,bookUpdated);
                            notifyDataSetChanged();

                            //txtName.setText(bookUpdated.getBook_Name());
                            //txtStatus.setText(bookUpdated.getBook_Status());
                            alertDialog.dismiss();
                        }

                    }
                });






            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                booksList.remove(book);
                notifyDataSetChanged();

            }
        });

        */

        return listViewItem;
    }




}
