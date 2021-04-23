package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private Button buttonLogout;
    private TextView textViewMessage;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getInputs();
        getSharedInformation();

        username = sp.getString("username","no username");

        textViewMessage.setText("Hi, "+ username);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,MainActivity.class));
                finish();
            }
        });
    }

    public void getInputs(){
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }
}