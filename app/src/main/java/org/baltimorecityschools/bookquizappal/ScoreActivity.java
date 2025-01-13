package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    int score;
    Intent welcomeToSA;
    Button sendScoreBTN;
    String initName;
    TextView greettv2;
    Button sendHighScorebtn;
    HighScoreEntry jake ;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent goToHighsc;
    LinearLayout Bground3;
    Intent goToFront;
    Button Backbtn;



    TextView Htv;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.baltimorecityschools.bookquizappal";
    private final String Name_KEY= "Name";
    private final String COLOR_KEY= "color";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV=findViewById(R.id.txtv);
        score=0;
        welcomeToSA=getIntent();
        score=welcomeToSA.getIntExtra("score",0);
        scoreTV.setText("" +  score);
        sendScoreBTN=findViewById(R.id.ebtn);
        greettv2=findViewById(R.id.greet2);
        sendHighScorebtn=findViewById(R.id.btton1);
        Backbtn=findViewById(R.id.backbtn);



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        Bground3 = (LinearLayout) findViewById(R.id.bacg3);
        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        int initialColor= mPreferences.getInt(COLOR_KEY, 0);


//copied

        if(initialColor == R.id.y){
            Bground3.setBackgroundColor(getColor(R.color.yel));
            Log.d(null,"yellow");
        }else if ( initialColor == R.id.g){
            Bground3.setBackgroundColor((getColor(R.color.green)));
        }else if ( initialColor == R.id.b){
            Bground3.setBackgroundColor(getColor(R.color.blue));
        }



        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        initName=mPreferences.getString(Name_KEY, "") ;
        greettv2.setText("hello " + initName + "!");

        sendHighScorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jake=new HighScoreEntry(initName, score);


                // Write a message to the database


                String key= myRef.push().getKey();

                myRef.child(key).setValue(jake);

                goToHighsc=new Intent(ScoreActivity.this, HighScoresActivity.class);
                startActivity(goToHighsc);
            }

        });
        sendScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses = new String[] {getString(R.string.stringaddress)};
                String subject = getString(R.string.nscore);
                String body=getString(R.string.screof) + score + getString(R.string.onapp);
                composeEmail(addresses, subject,body);
            }
        });




        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFront=new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(goToFront);
            }
        });

    }

    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }





}