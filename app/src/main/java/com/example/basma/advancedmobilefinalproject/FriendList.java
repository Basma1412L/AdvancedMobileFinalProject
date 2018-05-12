package com.example.basma.advancedmobilefinalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FriendList extends ArrayAdapter {


    private FirebaseAuth mAuth;
    FirebaseDatabase database ;

    private Activity context;
    private List<Book> searchList;
    private String userName;

    public FriendList(Activity context1, List<Book> searchList) {
        super(context1, R.layout.book_item_friend,searchList);
        this.context = context1;
        this.searchList = searchList;
    }

    public FriendList(Activity context1,String userName, List<Book> searchList) {
        super(context1, R.layout.book_item_friend,searchList);
        this.context = context1;
        this.searchList = searchList;
        this.userName=userName;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View  listViewItem = inflater.inflate(R.layout.book_item_friend,null,true);

        final TextView txtName=(TextView)listViewItem.findViewById(R.id.bookName2);
        final  TextView txtStatus=(TextView)listViewItem.findViewById(R.id.bookStatus2);
        Button exchange = (Button)listViewItem.findViewById(R.id.exchange2);
        final Book book =searchList.get(position);

        txtName.setText(book.getBook_Name());
        txtStatus.setText(book.getBook_Status());




        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                /////




             final   AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = context.getLayoutInflater();
                final View displayView = inflater.inflate(R.layout.exchange_source, null);
                dialogBuilder.setView(displayView);
                final Spinner requested = (Spinner) displayView.findViewById(R.id.mybooks);
                final Spinner exchanged = (Spinner) displayView.findViewById(R.id.friendbooks);
                String selectedRequest="";
                Button register = (Button) displayView.findViewById(R.id.doneBtn);


                mAuth = FirebaseAuth.getInstance();

              final  FirebaseUser currentUser = mAuth.getCurrentUser();
                String userID = currentUser.getUid();
                final DatabaseReference database = Utils.getDatabase().getReference();
                final DatabaseReference ref = database.child("User").child(userID);




                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Boolean userFound = false;
                        User profileUser = dataSnapshot.getValue(User.class);
                        List<Book> userBooks = profileUser.getBooks();
                        int size = userBooks.size();
                        String[] arraySpinner = new String[size];
                        for (int i = 0; i < size; i++) {
                            arraySpinner[i] = userBooks.get(i).getBook_Name();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arraySpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        exchanged.setAdapter(adapter);


                        int size2 = searchList.size();
                        String[] requestedArray=new String[size2];

                        if (size2==0)
                        {
                            for (int i = 0; i < 4; i++) {
                                requestedArray[i] ="Nothing";
                            }
                        }
                        else
                        {
                            for (int i = 0; i < size2; i++) {
                            requestedArray[i] = searchList.get(i).getBook_Name();
                        }
                        }

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, requestedArray);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        requested.setAdapter(adapter2);
                        dialogBuilder.setTitle("Updating book status");
                        userFound = true;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();




                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseDatabase database = Utils.getDatabase();
                        DatabaseReference myRef2 = database.getReference("User");

                        myRef2.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                for(DataSnapshot data : dataSnapshot.getChildren())
                                {
                                    String usernameR = data.child("name").getValue(String.class);
                                    String useremailR =data.child("email").getValue(String.class);
                                    if(usernameR.equals(userName)) {
                                        DataSnapshot ds=data.child("books");
                                        for (DataSnapshot dsBook: ds.getChildren()) {
                                            Book b = dsBook.getValue(Book.class);
                                            if(b.getBook_Name().equals(requested.getSelectedItem().toString())) {

                                                String userEmail = currentUser.getEmail();
                                                if (useremailR.equals(userEmail)) {

                                          //          Toast.makeText(context, "Hey you can't exchange a book with yourself 0_0 !", Toast.LENGTH_LONG).show();
                                                } else {
                                                    String status = b.getBook_Status();
                                                    if (status.equals("Free")) {
                                                        b.setBook_Status("Requested");
                                                        ExchangeRequest exchangeRequest=new ExchangeRequest(userEmail,exchanged.getSelectedItem().toString());
                                                        b.setExchange_request(exchangeRequest);
                                                        dsBook.getRef().setValue(b);
                                                        break;
                                                    }
                                                    else if (status.equals("Requested")) {
                                                   //     Toast.makeText(context, "This book is already requested, try something else", Toast.LENGTH_LONG).show();
                                                    }
                                                    else {
                                                //        Toast.makeText(context, "This book can not be requested, try something else", Toast.LENGTH_LONG).show();

                                                    }
                                                }
                                            }

                                        }
                                        alertDialog.dismiss();


                                    }
                                }



                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });



                    }
                });



            }
        });

                return listViewItem;
            }
            }








