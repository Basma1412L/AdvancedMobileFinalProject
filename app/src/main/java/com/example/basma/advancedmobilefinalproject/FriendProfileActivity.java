package com.example.basma.advancedmobilefinalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class FriendProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database ;

    TextView nametxt;
    TextView facultytxt;
    TextView emailtxt;
    TextView yeartxt;
    ImageView profilePicture;
    ListView searchListView;
    FriendList adapter;



    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        nametxt=(TextView)findViewById(R.id.nameTxt);
        emailtxt=(TextView)findViewById(R.id.emailTxt);
        facultytxt=(TextView)findViewById(R.id.facultyTxt);
        yeartxt=(TextView)findViewById(R.id.yearTxt);
        profilePicture=(ImageView)findViewById(R.id.imageView);
        searchListView = (ListView)findViewById(R.id.booksList);

        database = Utils.getDatabase();

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference database = Utils.getDatabase().getReference();
        DatabaseReference ref = database.child("User").child(id);

        try
        {

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Boolean userFound = false;

                            User profileUser=dataSnapshot.getValue(User.class);
                            nametxt.setText(profileUser.getName());
                            emailtxt.setText(profileUser.email);
                            facultytxt.setText(profileUser.getFaculty());
                            yeartxt.setText(profileUser.getYears());

                            try {
                                Bitmap imageBitmap = decodeFromFirebaseBase64(profileUser.getProfilePicture());
                                profilePicture.setImageBitmap(imageBitmap);
                            }
                            catch (Exception e )
                            {

                            }
                            List<Book> userBook = profileUser.getBooks();
                    adapter = new FriendList(FriendProfileActivity.this,profileUser.getName(),userBook);
                    searchListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    userFound = true;

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        }

        catch(Exception e)
        {


        }



    }
}
