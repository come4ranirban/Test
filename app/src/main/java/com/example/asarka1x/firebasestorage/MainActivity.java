package com.example.asarka1x.firebasestorage;

import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        final TextView text= (TextView)findViewById(R.id.img);

        mStorageRef = FirebaseDatabase.getInstance().getReference("image");

        mStorageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    text.setText(Html.fromHtml(dataSnapshot.child("txt").getValue().toString(), Html.FROM_HTML_MODE_COMPACT));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
