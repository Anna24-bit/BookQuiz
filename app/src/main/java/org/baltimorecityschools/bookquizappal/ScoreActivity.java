package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button sendScoreBTN;



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
        sendScoreBTN=findViewById(R.id.ebtn);

        sendScoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] addresses = new String[] {getString(R.string.stringaddress)};
                String subject = getString(R.string.nscore);
                String body=getString(R.string.screof) + score + getString(R.string.onapp);
                composeEmail(addresses, subject,body);
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