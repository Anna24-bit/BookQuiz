package org.baltimorecityschools.bookquizappal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView questTV;
    Button trueBTN,falsBTN,nextbn;
    int duration;
    String toastMsg;
    Toast myToast;
    int score;
    Intent goToSA;
    Question q1,q2,q3,q4,q5,q6,q7,q8,q9,q10, currentQ, hints;
    Question[] questions;
    int currentIndex;
    Button hintBTN;
    Button setButton;
    Intent goToset;
    TextView Htv;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.baltimorecityschools.bookquizappal";
    private final String Name_KEY= "Name";
    private final String COLOR_KEY= "color";
    Intent goToHighsc;
    LinearLayout Bground4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        q1 = new Question(getString(R.string.q1Text),false,getString(R.string.hint1));
        q2 = new Question(getString(R.string.q2T),false,getString(R.string.hint2));
        q3 = new Question(getString(R.string.q3T),true,getString(R.string.hint3));
        q4 = new Question(getString(R.string.q4T),false,getString(R.string.hint4));
        q5 = new Question(getString(R.string.q5T),false,getString(R.string.hint5));
        q6 = new Question(getString(R.string.q6T),true,getString(R.string.hint6));
        q7 = new Question(getString(R.string.qT7),true,getString(R.string.hint7));
        q8 = new Question(getString(R.string.qT8),true,getString(R.string.hint8));
        q9 = new Question(getString(R.string.qT9),false,getString(R.string.hint9));
        q10 = new Question(getString(R.string.qT10),true,getString(R.string.hint10));
        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        currentIndex=0;
        currentQ=q1;
        questions=new Question[] {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10};

        score=0;
        duration=0;
        questTV=findViewById(R.id.questtv1);
        nextbn=findViewById(R.id.nxtbtn);
        trueBTN=findViewById(R.id.trbtn);
        falsBTN=findViewById(R.id.flbtn);
        toastMsg=getString(R.string.toastrighta);
        hintBTN=findViewById(R.id.Hbtn);
        setButton=findViewById(R.id.setbtn);
        Htv=findViewById(R.id.grtnm);
        Htv.setText("hello " + mPreferences.getString(Name_KEY, "") + "!");
        Bground4 = (LinearLayout) findViewById(R.id.bacg4);
        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        int initialColor= mPreferences.getInt(COLOR_KEY, 0);


//copied

        if(initialColor == R.id.y){
            Bground4.setBackgroundColor(getColor(R.color.yel));
            Log.d(null,"yellow");
        }else if ( initialColor == R.id.g){
            Bground4.setBackgroundColor((getColor(R.color.green)));
        }else if ( initialColor == R.id.b){
            Bground4.setBackgroundColor(getColor(R.color.blue));
        }



        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToset=new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(goToset);
            }
        });




        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,currentQ.getHints(), Snackbar.LENGTH_LONG).setAction("",null).show();
                score-=2;

            }

        });



        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQ.getCorrAns()==true){
                    toastMsg=getString(R.string.toastright1);
                    score+=5;

                }

                else{
                    toastMsg=getString(R.string.toastwrong1);
                }

                duration= Toast.LENGTH_SHORT;
                myToast=Toast.makeText(MainActivity.this,toastMsg,duration);
                myToast.show();
            }
        });


        falsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentQ.getCorrAns()==false){
                    toastMsg=getString(R.string.toastright);
                    score+=5;

                }

                else{
                    toastMsg=getString(R.string.toastwrong);
                }

                duration= Toast.LENGTH_SHORT;
                myToast=Toast.makeText(MainActivity.this,toastMsg,duration);
                myToast.show();
            }
        });


        nextbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex>=9){

                    goToSA=new Intent(MainActivity.this, ScoreActivity.class);
                    goToSA.putExtra("score",score);
                    startActivity(goToSA);

                }
                else{
                    currentIndex++;
                    Log.d("AAAA", String.valueOf(currentIndex));
                    currentQ=questions[currentIndex];
                    Log.d("AAAA", currentQ.toString());
                    questTV.setText(currentQ.getQText());

                }

            }
        });


    }

   @Override
    protected void onResume( ){
       super.onResume();
       Htv.setText("hello " + mPreferences.getString(Name_KEY, "") + "!");
    }
}