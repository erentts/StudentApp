
package tr.edu.yildiz.erentutus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

import tr.edu.yildiz.erentutus.entity.User;

public class Profile extends AppCompatActivity {
    private Button buttonLogout,buttonAddQuestion,buttonListQuestions,ButtonCreateExam,ButtonExamManager;
    private TextView textViewMessage;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;
    private ImageView Thumbnail;
    String examTime,examPoint,examDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getInputs();
        getSharedInformation();

        username = sp.getString("username","no username");
        examTime = sp.getString("time","no time");
        examPoint = sp.getString("point","no point");
        examDifficulty = sp.getString("difficulty","no difficulty");
        Thumbnail.setImageResource(R.drawable.five);


        textViewMessage.setText("Hi, "+ username);
        //textViewMessage.setText(getThumbnailPath(username));


        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,MainActivity.class));
                finish();
            }
        });

        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,AddQuestion.class));
            }
        });

        buttonListQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,ListQuestions.class));
            }
        });

        ButtonExamManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,ExamManager.class));
            }
        });

        ButtonCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("time",examTime);
                editor.putString("point",examPoint);
                editor.putString("difficulty",examDifficulty);
                editor.commit();
                startActivity(new Intent(Profile.this,CreateExam.class));
            }
        });



    }

    public void getInputs(){
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        buttonAddQuestion = (Button) findViewById(R.id.buttonAddQuestion);
        buttonListQuestions = (Button) findViewById(R.id.buttonListQuestions);
        ButtonCreateExam = (Button) findViewById(R.id.ButtonCreateExam);
        ButtonExamManager = (Button) findViewById(R.id.ButtonExamManager);
        Thumbnail = (ImageView) findViewById(R.id.Thumbnail);
    }

    public void getSharedInformation(){
        sp = getSharedPreferences("LogInInformation",MODE_PRIVATE);
        editor = sp.edit();
    }

    public String getThumbnailPath(String username){
        final String[] x = new String[1];
        User.users.forEach((data)-> {
            if(username.toString().equals(data.getUsername())){
                x[0] = data.getPhoto();
            }
        });
        return x[0];
    }

}