package org.baltimorecityschools.bookquizappal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    String name;

    TextView greettv;

    EditText nameET;

    Button Click;
    Intent welcomeToset;


    LinearLayout Bground;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.baltimorecityschools.bookquizappal";
    private final String Name_KEY= "Name";
    private final String COLOR_KEY= "color";






    //initialize the shared preferences
    Button redBtn;
    Button greenBtn;
    Button blueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        greettv=(TextView)findViewById(R.id.greetings);
        nameET=(EditText)findViewById(R.id.username);
        Click=(Button)findViewById(R.id.btton);
        welcomeToset=getIntent();
        mPreferences=getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        redBtn= (Button) findViewById(R.id.y);
        greenBtn = (Button) findViewById(R.id.g);
        blueBtn = (Button) findViewById(R.id.b);
        Bground = (LinearLayout) findViewById(R.id.bacg);
        int initialColor= mPreferences.getInt(COLOR_KEY, 0);



        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=nameET.getText().toString();

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                preferencesEditor.putString(Name_KEY, name);

                preferencesEditor.apply();

                greettv.setText("hello " + mPreferences.getString(Name_KEY, "") + "!");
                finish();



            }
        });




        if(initialColor == R.id.y){
            Bground.setBackgroundColor(getColor(R.color.yel));
            Log.d(null,"yellow");
        }else if ( initialColor == R.id.g){
            Bground.setBackgroundColor((getColor(R.color.green)));
        }else if ( initialColor == R.id.b){
            Bground.setBackgroundColor(getColor(R.color.blue));
        }



        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bground.setBackgroundColor(getColor(R.color.yel));
                selectColor(view);
            }
        });

        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bground.setBackgroundColor(getColor(R.color.green));
                selectColor(view);
            }
        });

        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bground.setBackgroundColor(getColor(R.color.blue));
                selectColor(view);
            }
        });


    }

    public void selectColor(View view) {

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        preferencesEditor.putInt(COLOR_KEY, view.getId());

        preferencesEditor.apply();

        if(view.getId() == R.id.y){
            Bground.setBackgroundColor(getColor(R.color.yel));
            Log.d(null,"yellow");
        }else if (view.getId() == R.id.g){
            Bground.setBackgroundColor((getColor(R.color.green)));
        }else if (view.getId() == R.id.b){
            Bground.setBackgroundColor(getColor(R.color.blue));
        }
    }



}