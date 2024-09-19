package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    int score;
    Intent welcomeToSA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        scoreTV=findViewById(R.id.txtv);
        score=0;
        welcomeToSA=getIntent();
        score=welcomeToSA.getIntExtra("score",0);
        scoreTV.setText("" +  score);



    }
}