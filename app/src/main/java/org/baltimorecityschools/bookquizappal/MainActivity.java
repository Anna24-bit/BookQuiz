package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.os.Bundle;
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

        q1 = new Question("American panda is written by Tracy Deon ?",false);
        q2 = new Question("the movie speak came out before the book ?",false);
        q3 = new Question("No longer human is about an individuals alienation from society  ?",true);
        q4 = new Question("No longer human took place in china  ?",false);
        q5 = new Question("grayson Hawthorn is the MC in inheritance game  ?",false);
        q6 = new Question("The book harry potter  was released before the movie  ?",true);
        q7 = new Question("To kill a mocking bird is based on a true story ?",true);
        q8 = new Question(" legend born is a trilogy?",true);
        q9 = new Question(" The great gatsby was published in 2002",false);
        q10 = new Question(" if beale streets could talk was written by james baldwin",true);



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
                    currentQ=questions[currentIndex];
                    questTV.setText(currentQ.getQText());

                }

            }
        });


    }
}