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

public class MainActivity extends AppCompatActivity {
    TextView questTV;
    Button trueBTN,falsBTN,nextbn;
    int duration;
    String toastMsg;
    Toast myToast;
    int score;
    Intent goToSA;
    Question q1,q2,q3,q4,q5,q6,q7,q8,q9,q10, currentQ;
    Question[] questions;
    int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        q1 = new Question(getString(R.string.q1Text),false);
        q2 = new Question(getString(R.string.q2T),false);
        q3 = new Question(getString(R.string.q3T),true);
        q4 = new Question(getString(R.string.q4T),false);
        q5 = new Question(getString(R.string.q5T),false);
        q6 = new Question(getString(R.string.q6T),true);
        q7 = new Question(getString(R.string.qT7),true);
        q8 = new Question(getString(R.string.qT8),true);
        q9 = new Question(getString(R.string.qT9),false);
        q10 = new Question(getString(R.string.qT10),true);



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

        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQ.getCorrAns()==true){
                    toastMsg="You got it right";
                    score++;

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
                    score++;

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