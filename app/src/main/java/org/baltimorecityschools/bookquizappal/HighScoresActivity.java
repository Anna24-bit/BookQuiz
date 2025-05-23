package org.baltimorecityschools.bookquizappal;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class HighScoresActivity extends AppCompatActivity {


    LinearLayout Bground2;


    TextView user1;
    TextView user2;
    TextView user3;
    TextView uscore1;
    TextView uscore2;
    TextView uscore3;


    FirebaseDatabase database;
    DatabaseReference myRef;

     ArrayList<HighScoreEntry> scoreEntries;
     HighScoreEntry scoreEntry;

    TextView Htv;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.baltimorecityschools.bookquizappal";
    private final String Name_KEY= "Name";
    private final String COLOR_KEY= "color";

    private final String TAG="the tag";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        user1=findViewById(R.id.u1);
        uscore1=findViewById(R.id.us1);
        user2=findViewById(R.id.u2);
        uscore2=findViewById(R.id.us2);
        user3=findViewById(R.id.u3);
        uscore3=findViewById(R.id.us3);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        scoreEntries= new ArrayList<HighScoreEntry>();

//copied
        Bground2 = (LinearLayout) findViewById(R.id.bacg2);
        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        int initialColor= mPreferences.getInt(COLOR_KEY, 0);



//copied

        if(initialColor == R.id.y){
            Bground2.setBackgroundColor(getColor(R.color.yel));
            Log.d(null,"yellow");
        }else if ( initialColor == R.id.g){
            Bground2.setBackgroundColor((getColor(R.color.green)));
        }else if ( initialColor == R.id.b){
            Bground2.setBackgroundColor(getColor(R.color.blue));
        }



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot HSEsnapShot : dataSnapshot.getChildren()){
                    HighScoreEntry value = HSEsnapShot.getValue(HighScoreEntry.class);
                    Log.d(TAG, "Value is: " + value.getName());
                    scoreEntries.add(value);

                }
                Collections.sort(scoreEntries);

                for ( HighScoreEntry hse : scoreEntries) {
//                    System.out.println(hse.getName());

                    Log.d(TAG,hse.getName());
                }

                scoreEntry= scoreEntries.get(0);
                user1.setText(scoreEntry.getName());
                uscore1.setText(scoreEntry.getScore() + " ");

                scoreEntry= scoreEntries.get(1);
                user2.setText(scoreEntry.getName());
                uscore2.setText(scoreEntry.getScore() + " ");

                scoreEntry= scoreEntries.get(2);
                user3.setText(scoreEntry.getName());
                uscore3.setText(scoreEntry.getScore() + " ");



            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }



}