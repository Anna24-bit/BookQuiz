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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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
                toastMsg="You got it right";
                score++;
                duration= Toast.LENGTH_SHORT;
                myToast=Toast.makeText(MainActivity.this,toastMsg,duration);
                myToast.show();
            }
        });

        falsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMsg="You got it wrong";
                duration= Toast.LENGTH_SHORT;
                myToast=Toast.makeText(MainActivity.this,toastMsg,duration);
                myToast.show();
            }
        });


        nextbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSA=new Intent(MainActivity.this, ScoreActivity.class);
                goToSA.putExtra("score",score);
                startActivity(goToSA);
            }
        });


    }
}