package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        q1 = new Question(getString(R.string.q1Text),false,"American panda was written by the same author of our wayward fate");
        q2 = new Question(getString(R.string.q2T),false," book was published 1999");
        q3 = new Question(getString(R.string.q3T),true,"The MC finds people strange");
        q4 = new Question(getString(R.string.q4T),false,"The MC was born in Tokyo");
        q5 = new Question(getString(R.string.q5T),false,"the MC has a half sister");
        q6 = new Question(getString(R.string.q6T),true,"harry potter was book was first published in 1997");
        q7 = new Question(getString(R.string.qT7),true,"two black men were accused of murder in alabama");
        q8 = new Question(getString(R.string.qT8),true,"The answers to bree's quest in book two will be revealed in a following book");
        q9 = new Question(getString(R.string.qT9),false,"The author died in 1940");
        q10 = new Question(getString(R.string.qT10),true,"james baldwin wrote a story about two lovers from NYC who experienced racism in their city");



        currentIndex=0;
        currentQ=q1;
        questions=new Question[] {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10};

        score=0;
        duration=0;
        questTV=findViewById(R.id.questtv1);
        nextbn=findViewById(R.id.nxtbtn);
        trueBTN=findViewById(R.id.trbtn);
        falsBTN=findViewById(R.id.flbtn);
        toastMsg="You got the question wrong";
        hintBTN=findViewById(R.id.Hbtn);

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
                    toastMsg="You got it right";
                    score+=5;

                }

                else{
                    toastMsg="You got the question wrong";
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
                    toastMsg="You got it right";
                    score+=5;

                }

                else{
                    toastMsg="You got the question wrong";
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
}